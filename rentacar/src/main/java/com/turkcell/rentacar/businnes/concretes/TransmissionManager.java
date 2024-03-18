package com.turkcell.rentacar.businnes.concretes;


import com.turkcell.rentacar.businnes.abstracts.TransmissionService;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperManager;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.dtos.reponses.CreateTransmissionResponse;
import com.turkcell.rentacar.dtos.requests.CreateTransmissionRequest;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransmissionManager implements TransmissionService {
    TransmissionRepository transmissionRepository; // IoC
    private static final String transmissionNotFoundMessage = "Transmission not found";
    private static final String transmissionAlreadyExistsMessage = "Transmission already exists";
    private ModelMapperManager modelMapperService;


    @Override
    public CreateTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        Transmission transmission =this.modelMapperService.forRequest().map(createTransmissionRequest,Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());

        Transmission createdTransmission = transmissionRepository.save(transmission);

        CreateTransmissionResponse createTransmissionResponse= this.modelMapperService.forResponse().map(createdTransmission,CreateTransmissionResponse.class);;

        return createTransmissionResponse;
    }

    @Override
    public Transmission update(Transmission transmission) {
        // TODO: Validation rules
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findById(transmission.getId());
        transmissionShouldBeExist(foundOptionalTransmission);
        transmissionNameCanNotBeDuplicatedWhenUpdated(transmission);

        Transmission transmissionToUpdate = foundOptionalTransmission.get();
        transmissionToUpdate.setName(transmission.getName()); // TODO: mapper

        return transmissionRepository.save(transmissionToUpdate);
    }

    @Override
    public void delete(int id) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findById(id);
        transmissionShouldBeExist(foundOptionalTransmission);
        transmissionRepository.delete(foundOptionalTransmission.get());
    }

    @Override
    public List<Transmission> getAll() {
        return transmissionRepository.findAll();
    }

    @Override
    public Transmission get(int id) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findById(id);
        transmissionShouldBeExist(foundOptionalTransmission);
        return foundOptionalTransmission.get();
    }

    // temp business rules
    // TODO: TransmissionBusinessRules
    private void transmissionShouldBeExist(Optional<Transmission> transmission) {
        if (transmission.isEmpty()) {
            throw new RuntimeException(transmissionNotFoundMessage);
        }
    }

    private void transmissionNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.getByNameIgnoreCase(name.trim());
        if (foundOptionalTransmission.isPresent()) {
            throw new RuntimeException(transmissionAlreadyExistsMessage);
        }
    }

    private void transmissionNameCanNotBeDuplicatedWhenUpdated(Transmission transmission) {
        boolean exists = transmissionRepository.existsByNameIgnoreCaseAndIdIsNot(transmission.getName().trim(), transmission.getId());
        if (exists) {
            throw new RuntimeException(transmissionAlreadyExistsMessage);
        }
    }
}