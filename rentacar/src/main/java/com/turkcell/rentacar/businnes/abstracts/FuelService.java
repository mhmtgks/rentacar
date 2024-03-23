package com.turkcell.rentacar.businnes.abstracts;


import com.turkcell.rentacar.dtos.reponses.CreateFuelResponse;
import com.turkcell.rentacar.dtos.reponses.GetFuelResponse;
import com.turkcell.rentacar.dtos.reponses.UpdateFuelResponse;
import com.turkcell.rentacar.dtos.requests.CreateBrandRequest;
import com.turkcell.rentacar.dtos.requests.CreateFuelRequest;
import com.turkcell.rentacar.dtos.requests.UpdateFuelRequest;
import com.turkcell.rentacar.entities.concretes.Fuel;

import java.util.List;

public interface FuelService {
    CreateFuelResponse add(CreateFuelRequest createFuelRequest);

    UpdateFuelResponse update(UpdateFuelRequest updateFuelRequest);

    void delete(int id);

    List<GetFuelResponse> getAll();

    GetFuelResponse get(int id);
}
