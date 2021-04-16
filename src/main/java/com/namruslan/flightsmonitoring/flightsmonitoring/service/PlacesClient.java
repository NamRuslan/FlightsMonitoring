package com.namruslan.flightsmonitoring.flightsmonitoring.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.PlacesDto;

import java.io.IOException;
import java.util.List;


/**
 * SkyScanner client
 */
public interface PlacesClient {

    /**
     * Get a list of places that matches a query string based on args
     *
     * @param query code of city
     * @param country code of country
     * @param currency code of currency
     * @param locale code of locale
     * @return collection of {@link PlacesDto} objects
     * @throws IOException
     * @throws UnirestException
     */
    List<PlacesDto> retrieveListPlaces(String query, String country, String currency, String locale) throws IOException, UnirestException;
}
