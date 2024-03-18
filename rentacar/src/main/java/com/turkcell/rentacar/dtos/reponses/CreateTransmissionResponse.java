package com.turkcell.rentacar.dtos.reponses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTransmissionResponse {

    private int Id;

    private String name;

    private LocalDateTime createdDate;
}
