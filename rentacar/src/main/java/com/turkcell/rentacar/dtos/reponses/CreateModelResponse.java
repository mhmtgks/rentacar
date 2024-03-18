package com.turkcell.rentacar.dtos.reponses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateModelResponse {

    private int Id;

    private String name;

    private LocalDateTime createdDate;

    private Double dailyPrice;


}
