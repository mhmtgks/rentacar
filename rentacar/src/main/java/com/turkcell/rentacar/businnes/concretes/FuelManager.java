package com.turkcell.rentacar.businnes.concretes;


import com.turkcell.rentacar.businnes.abstracts.FuelService;
import com.turkcell.rentacar.businnes.rules.FuelBusinnesRules;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FuelManager implements FuelService {
     private FuelRepository fuelRepository;

    private FuelBusinnesRules   fuelBusinnesRules;
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

        fuelBusinnesRules.fuelShouldBeExist(foundOptionalFuel);
        fuelBusinnesRules.fuelNameCanNotBeDuplicated(fuel);

        Fuel fuelToUpdate = foundOptionalFuel.get();
        fuelToUpdate.setName(fuel.getName());
        UpdateFuelResponse updateFuelResponse= this.modelMapperService.forResponse().map(fuelToUpdate,UpdateFuelResponse.class);

        return updateFuelResponse;
    }

    @Override
    public void delete(int id) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.findById(id);
        fuelBusinnesRules.fuelShouldBeExist(foundOptionalFuel);
        fuelRepository.delete(foundOptionalFuel.get());


    }

    @Override
    public List<GetFuelResponse> getAll() {
        List<Fuel> fuels = fuelRepository.findAll();
        List<GetFuelResponse> fuelResponses = new ArrayList<>();

        for (var fuel : fuels) {
            GetFuelResponse getFuelResponse =
                    this.modelMapperService.forResponse().map(fuel, GetFuelResponse.class);
            fuelResponses.add(getFuelResponse);
        }

        return fuelResponses;
    }

    @Override
    public GetFuelResponse get(int id) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.findById(id);
        fuelBusinnesRules.fuelShouldBeExist(foundOptionalFuel);
        Fuel fuel =foundOptionalFuel.get();
        GetFuelResponse getFuelResponse =  this.modelMapperService.forResponse().map(fuel,GetFuelResponse.class);

        return getFuelResponse;
    }



   





















}