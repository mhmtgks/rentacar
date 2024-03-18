package com.turkcell.rentacar.businnes.concretes;

import com.turkcell.rentacar.businnes.abstracts.BrandService;
import com.turkcell.rentacar.businnes.rules.BrandBusinnesRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.dtos.reponses.CreateBrandResponse;
import com.turkcell.rentacar.dtos.requests.CreateBrandRequest;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;

    private ModelMapperService modelMapperService;
    private BrandBusinnesRules brandBusinnesRules;
    @Override
    public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand =  brandRepository.save(brand);
        CreateBrandResponse createdBrandResponse =this.modelMapperService.forResponse().map(createdBrand,CreateBrandResponse.class);
        return createdBrandResponse;
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



}
