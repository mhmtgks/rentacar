package com.turkcell.rentacar.businnes.abstracts;

import com.turkcell.rentacar.dtos.reponses.CreateModelResponse;
import com.turkcell.rentacar.dtos.requests.CreateModelRequest;
import com.turkcell.rentacar.entities.concretes.Model;

import java.util.List;

public interface ModelService {
    CreateModelResponse add(CreateModelRequest model);

    CreateModelResponse update(int id,CreateModelRequest model);

    void delete(int id);

    List<CreateModelResponse> getAll();

    CreateModelResponse get(int id);
}
