package com.namruslan.flightsmonitoring.flightsmonitoring.exception;


import com.namruslan.flightsmonitoring.flightsmonitoring.validation.ValidationErrorDto;

import java.util.List;

public class FlightClientException extends RuntimeException{

    private List<ValidationErrorDto> validationErrorDtos;

    public FlightClientException(String message) {
        super(message);
    }

    public FlightClientException(String message, List<ValidationErrorDto> validationErrorDtos) {
        super(message);
        this.validationErrorDtos = validationErrorDtos;
    }

    public FlightClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
