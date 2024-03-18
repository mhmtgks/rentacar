package com.turkcell.rentacar.businnes.abstracts;

import com.turkcell.rentacar.dtos.reponses.CreateBrandResponse;
import com.turkcell.rentacar.dtos.requests.CreateBrandRequest;
import com.turkcell.rentacar.entities.concretes.Brand;

import java.util.List;

public interface BrandService {
    CreateBrandResponse add(CreateBrandRequest createBrandRequest);

    Brand update(Brand brand);

    void delete(int id);

    List<Brand> getAll();

    Brand get(int id);

}
