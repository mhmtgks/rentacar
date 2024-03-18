package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.businnes.abstracts.BrandService;
import com.turkcell.rentacar.dtos.reponses.CreateBrandResponse;
import com.turkcell.rentacar.dtos.requests.CreateBrandRequest;
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
    public CreateBrandResponse add(@RequestBody CreateBrandRequest createBrandRequest){
        return brandService.add(createBrandRequest);
    }

}
