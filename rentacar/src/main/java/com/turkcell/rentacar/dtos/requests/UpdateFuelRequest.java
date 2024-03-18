package com.turkcell.rentacar.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateFuelRequest {

    @NotNull
    private int id;

    @NotNull
    private String name;


}
