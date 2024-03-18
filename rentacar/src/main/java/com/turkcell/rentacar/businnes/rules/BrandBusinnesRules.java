package com.turkcell.rentacar.businnes.rules;


import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandBusinnesRules {

    BrandRepository brandRepository;
    public void brandNameCanNotBeDuplicated(String brandName){
        Optional<Brand> brand =brandRepository.findByNameIgnoreCase(brandName);

        if(brand.isPresent()){
            throw new RuntimeException("BrandExists");
        }




    }


}
