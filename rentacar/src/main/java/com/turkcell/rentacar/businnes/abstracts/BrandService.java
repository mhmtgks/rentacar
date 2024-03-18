package com.turkcell.rentacar.businnes.abstracts;

import com.turkcell.rentacar.entities.concretes.Brand;

import java.util.List;

public interface BrandService {
    Brand add(Brand brand);

    Brand update(Brand brand);

    void delete(int id);

    List<Brand> getAll();

    Brand get(int id);

}
