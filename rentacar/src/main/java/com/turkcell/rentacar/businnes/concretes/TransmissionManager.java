package com.turkcell.rentacar.businnes.concretes;


import com.turkcell.rentacar.businnes.abstracts.TransmissionService;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperManager;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.dtos.reponses.CreateTransmissionResponse;
import com.turkcell.rentacar.dtos.requests.CreateTransmissionRequest;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransmissionManager implements TransmissionService {

    private TransmissionRepository transmissionRepository;
    private ModelMapperService modelMapperService;


    @Override
    public CreateTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        Transmission transmission = this.modelMapperService.forRequest().map(createTransmissionRequest,Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission = transmissionRepository.save(transmission);
        CreateTransmissionResponse createdTransmissionResponse =
                this.modelMapperService.forResponse().map(createdTransmission,CreateTransmissionResponse.class);
        return createdTransmissionResponse;
    }

    @Override
    public List<CreateTransmissionResponse> getAll() {
        List<Transmission> transmissions = transmissionRepository.findAll();
        List<CreateTransmissionResponse> transmissionResponses = new ArrayList<>();
        for (var transmission: transmissions){
            CreateTransmissionResponse createdTransmissionResponse =
                    this.modelMapperService.forResponse().map(transmission,CreateTransmissionResponse.class);
            transmissionResponses.add(createdTransmissionResponse);
        }
        return transmissionResponses;
    }

    @Override
    public CreateTransmissionResponse get(int id) {
        Optional<Transmission> transmissionOptional = transmissionRepository.findById(id);
        Transmission transmission = transmissionOptional.get();
        CreateTransmissionResponse createdTransmissionResponse =
                this.modelMapperService.forResponse().map(transmission,CreateTransmissionResponse.class);

        return createdTransmissionResponse;
    }

    @Override
    public CreateTransmissionResponse update(int id, CreateTransmissionRequest createTransmissionRequest) {
        Optional<Transmission> transmissionOptional = transmissionRepository.findById(id);
        if (transmissionOptional.isPresent()){
            Transmission transmission = transmissionOptional.get();
            this.modelMapperService.forRequest().map(createTransmissionRequest,transmission);
            transmission.setUpdatedDate(LocalDateTime.now());

            Transmission updatedTransmission = transmissionRepository.save(transmission);

            CreateTransmissionResponse createdTransmissionResponse =
                    this.modelMapperService.forResponse().map(updatedTransmission,CreateTransmissionResponse.class);

            return createdTransmissionResponse;
        }
        else {
            return null;
        }
    }

    @Override
    public void delete(int id) {

    }
/*
    @Override
    public List<Transmission> getAll() {
        return transmissionRepository.findAll();
    }

    @Override
    public Transmission getById(int id) {
        return transmissionRepository.findById(id).orElse(null);
    }

    @Override
    public Transmission update(int id, Transmission transmission) {
        Transmission updatedTransmission = transmissionRepository.findById(id).orElse(null);
        updatedTransmission.setName(transmission.getName());
        return transmissionRepository.save(updatedTransmission);
    }

    @Override
    public void delete(int id) {
        transmissionRepository.deleteById(id);
    }
 */
}