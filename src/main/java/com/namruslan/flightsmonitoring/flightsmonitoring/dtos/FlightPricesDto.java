package com.namruslan.flightsmonitoring.flightsmonitoring.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FlightPricesDto {

    @JsonProperty("Quotes")
    private List<QuotesDto> quotes;

    @JsonProperty("Carriers")
    private List<CarriersDto> carriers;

    @JsonProperty("Places")
    private List<BrowsePlacesDto> places;

    @JsonProperty("Currencies")
    private List<CurrencyDto> currencies;

}
