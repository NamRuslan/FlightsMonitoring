package com.namruslan.flightsmonitoring.flightsmonitoring.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OutboundLegDto {

    @JsonProperty("CarrierIds")
    private List<Integer> carrierIds;

    @JsonProperty("OriginId")
    private Integer originId;

    @JsonProperty("DestinationId")
    private Integer destinationId;

    @JsonProperty("DepartureDate")
    private String departureDate;

}
