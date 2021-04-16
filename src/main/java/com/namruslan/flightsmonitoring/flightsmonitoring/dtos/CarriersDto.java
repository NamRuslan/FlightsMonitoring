package com.namruslan.flightsmonitoring.flightsmonitoring.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarriersDto {

    @JsonProperty("CarrierId")
    private Integer carrierId;

    @JsonProperty("Name")
    private String name;

}
