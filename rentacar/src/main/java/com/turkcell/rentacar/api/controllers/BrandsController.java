package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.businnes.abstracts.BrandService;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandsController {
    private BrandService brandService; //IoC

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand add(@RequestBody Brand brand){
        return brandService.add(brand);
    }

}
