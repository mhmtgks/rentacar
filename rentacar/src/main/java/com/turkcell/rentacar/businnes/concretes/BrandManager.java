package com.turkcell.rentacar.businnes.concretes;

import com.turkcell.rentacar.businnes.abstracts.BrandService;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    @Override
    public Brand add(Brand brand) {

        //todo : businnes rules


        Brand createdBrand = brandRepository.save(brand);

        return createdBrand;
    }

    @Override
    public Brand update(Brand brand) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Brand> getAll() {
        return null;
    }

    @Override
    public Brand get(int id) {
        return null;
    }


    private BrandRepository brandRepository;
}
