package com.turkcell.rentacar.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBrandResponse {

    private String name;
    private int id;
    private LocalDateTime createdDate;


}
