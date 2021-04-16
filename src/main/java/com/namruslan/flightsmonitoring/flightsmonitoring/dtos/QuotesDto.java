package com.namruslan.flightsmonitoring.flightsmonitoring.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class QuotesDto {

    @JsonProperty("QuoteId")
    private Integer quoteId;

    @JsonProperty("MinPrice")
    private Integer minPrice;

    @JsonProperty("Direct")
    private boolean direct;

    @JsonProperty("OutboundLeg")
    private List<OutboundLegDto> outboundLeg;

    @JsonProperty("QuoteDateTime")
    private String QuoteDateTime;

}
