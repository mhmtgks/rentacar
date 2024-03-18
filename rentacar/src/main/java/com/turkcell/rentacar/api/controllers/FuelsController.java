package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.businnes.abstracts.FuelService;
import com.turkcell.rentacar.dtos.reponses.CreateFuelResponse;
import com.turkcell.rentacar.dtos.reponses.GetFuelResponse;
import com.turkcell.rentacar.dtos.reponses.UpdateFuelResponse;
import com.turkcell.rentacar.dtos.requests.CreateFuelRequest;
import com.turkcell.rentacar.dtos.requests.UpdateFuelRequest;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fuels")
public class FuelsController {
    private FuelService fuelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateFuelResponse add(@RequestBody CreateFuelRequest fuel) {

        return  fuelService.add(fuel);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdateFuelResponse update(@RequestBody UpdateFuelRequest fuel) {
        return fuelService.update(fuel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        fuelService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetFuelResponse get(@PathVariable int id) {
        return fuelService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Fuel> getAll() {
        return fuelService.getAll();
    }
}
