package com.turkcell.rentacar.businnes.concretes;


import com.turkcell.rentacar.businnes.abstracts.FuelService;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperManager;
import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.dtos.reponses.CreateFuelResponse;
import com.turkcell.rentacar.dtos.reponses.GetFuelResponse;
import com.turkcell.rentacar.dtos.reponses.UpdateFuelResponse;
import com.turkcell.rentacar.dtos.requests.CreateFuelRequest;
import com.turkcell.rentacar.dtos.requests.UpdateFuelRequest;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FuelManager implements FuelService {
    FuelRepository fuelRepository; // IoC
    private static final String fuelNotFoundMessage = "Fuel not found";
    private static final String fuelAlreadyExistsMessage = "Fuel already exists";
    private ModelMapperManager modelMapperService;


    @Override
    public CreateFuelResponse add(CreateFuelRequest createFuelRequest) {
        Fuel fuel = this.modelMapperService.forRequest().map(createFuelRequest,Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());


        Fuel createdFuel = fuelRepository.save(fuel);

        CreateFuelResponse createFuelResponse= this.modelMapperService.forResponse().map(createdFuel,CreateFuelResponse.class);

        return createFuelResponse;
    }

    @Override
    public UpdateFuelResponse update(UpdateFuelRequest updateFuelRequest) {

        Fuel fuel = this.modelMapperService.forRequest().map(updateFuelRequest,Fuel.class);
        fuel.setUpdatedDate(LocalDateTime.now());

        Optional<Fuel> foundOptionalFuel = fuelRepository.findById(fuel.getId());
        fuelShouldBeExist(foundOptionalFuel);
        fuelNameCanNotBeDuplicatedWhenUpdated(fuel);

        Fuel fuelToUpdate = foundOptionalFuel.get();
        fuelToUpdate.setName(fuel.getName()); // TODO: mapper
        UpdateFuelResponse updateFuelResponse= this.modelMapperService.forResponse().map(fuelToUpdate,UpdateFuelResponse.class);

        return updateFuelResponse;
    }

    @Override
    public void delete(int id) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.findById(id);
        fuelShouldBeExist(foundOptionalFuel);
        fuelRepository.delete(foundOptionalFuel.get());


    }

    @Override
    public List<Fuel> getAll() {
        return fuelRepository.findAll();
    }

    @Override
    public GetFuelResponse get(int id) {

        Optional<Fuel> foundOptionalFuel = fuelRepository.findById(id);
        fuelShouldBeExist(foundOptionalFuel);
        Fuel fuel =foundOptionalFuel.get();
        GetFuelResponse getFuelResponse =  this.modelMapperService.forResponse().map(fuel,GetFuelResponse.class);

        return getFuelResponse;
    }

    // temp business rules
    // TODO: FuelBusinessRules
    private void fuelShouldBeExist(Optional<Fuel> fuel) {
        if (fuel.isEmpty()) {
            throw new RuntimeException(fuelNotFoundMessage);
        }
    }

    private void fuelNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.getByNameIgnoreCase(name.trim());
        if (foundOptionalFuel.isPresent()) {
            throw new RuntimeException(fuelAlreadyExistsMessage);
        }
    }

    private void fuelNameCanNotBeDuplicatedWhenUpdated(Fuel fuel) {
        boolean exists = fuelRepository.existsByNameIgnoreCaseAndIdIsNot(fuel.getName().trim(), fuel.getId());
        if (exists) {
            throw new RuntimeException(fuelAlreadyExistsMessage);
        }
    }
}