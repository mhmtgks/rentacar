package com.turkcell.rentacar.core.utilities.exceptions.handlers;


import com.turkcell.rentacar.core.utilities.exceptions.problemDetails.BusinnesProblemDetails;
import com.turkcell.rentacar.core.utilities.exceptions.problemDetails.ValidationProblemDetails;
import com.turkcell.rentacar.core.utilities.exceptions.types.businnesException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice  // bütün istekleri kontrol edecel sınıf

public class GlobalExceptionHandler {
    @ExceptionHandler({businnesException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinnesProblemDetails handleBusinessException(businnesException exception) {
        BusinnesProblemDetails businessProblemDetails = new BusinnesProblemDetails();
        businessProblemDetails.setDetail(exception.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        Map<String,String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().stream().map(error -> //stream api nedir?
                validationErrors.put(error.getField(),error.getDefaultMessage())
        ).toList();
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(validationErrors);
        return validationProblemDetails;
    }
}
