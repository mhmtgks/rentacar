package com.turkcell.rentacar.core.utilities.exceptions.problemDetails;

public class BusinnesProblemDetails extends ProblemDetails{

    public BusinnesProblemDetails() {
        setTitle("Business Rule Violation");
        setType("http://mydomain.com/exceptions/business");
        setStatus("400");

    }
}
