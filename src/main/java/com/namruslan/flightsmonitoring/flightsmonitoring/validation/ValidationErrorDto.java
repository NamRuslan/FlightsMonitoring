package com.namruslan.flightsmonitoring.flightsmonitoring.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ValidationErrorDto {

    @JsonProperty("ParameterName")
    private String parameterName;

    @JsonProperty("ParameterValue")
    private String parameterValue;

    @JsonProperty("Message")
    private String message;


}
