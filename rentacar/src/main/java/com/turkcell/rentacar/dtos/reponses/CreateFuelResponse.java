package com.turkcell.rentacar.dtos.reponses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.boot.jaxb.internal.stax.LocalXmlResourceResolver;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateFuelResponse {


    private int Id;

    private LocalDateTime createdDate;

    private String name;



}
