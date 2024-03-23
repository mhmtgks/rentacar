package com.turkcell.rentacar.businnes.rules;

import com.turkcell.rentacar.core.utilities.exceptions.types.businnesException;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class FuelBusinnesRules {


    private FuelRepository fuelRepository;

    public void fuelShouldBeExist(Optional<Fuel> fuel) {
        if (fuel.isEmpty()) {
            throw new businnesException("Fuel not found");
        }
    }


    public void fuelNameCanNotBeDuplicated(Fuel fuel) {
        boolean exists = fuelRepository.existsByNameIgnoreCaseAndIdIsNot(fuel.getName().trim(), fuel.getId());
        if (exists) {
            throw new businnesException("Fuel exists");
        }
    }

}
