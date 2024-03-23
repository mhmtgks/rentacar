package com.turkcell.rentacar.businnes.abstracts;


import com.turkcell.rentacar.dtos.reponses.CreateTransmissionResponse;
import com.turkcell.rentacar.dtos.requests.CreateTransmissionRequest;
import com.turkcell.rentacar.entities.concretes.Transmission;

import java.util.List;

public interface TransmissionService {
    CreateTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);
    List<CreateTransmissionResponse> getAll();
    CreateTransmissionResponse get(int id);
    CreateTransmissionResponse update(int id, CreateTransmissionRequest createTransmissionRequest);
    void delete(int id);
}
