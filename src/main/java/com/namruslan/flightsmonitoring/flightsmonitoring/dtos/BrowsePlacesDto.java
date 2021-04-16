package com.namruslan.flightsmonitoring.flightsmonitoring.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrowsePlacesDto {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("PlaceId")
    private Integer placeId;

    @JsonProperty("IataCode")
    private String iataCode;

    @JsonProperty("SkyscannerCode")
    private String skyscannerCode;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("CityId")
    private String cityId;

    @JsonProperty("CountryName")
    private String countryName;

}
