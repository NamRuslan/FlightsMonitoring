package com.namruslan.flightsmonitoring.flightsmonitoring.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.CountryDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.CurrencyDto;

import java.io.IOException;
import java.util.List;

public interface LocalizationClient {

    /**
     * Retrieve market countries that SkyScanner flight API supports. Most suppliers (travel agencies or airlines)
     * set fares based on the market or country of purchase.
     * It is therefore necessary to specify the market country in every query.
     *
     * @param locale locale of the response
     * @return the collection of {@link CountryDto} objects
     * @throws java.io.IOException
     */
    List<CountryDto> retrieveCountries(String locale) throws IOException;

    /**
     * Retrieve the currencies that we search with SkyScanner API
     *
     * @return the collection of the {@link CurrencyDto} objects.
     * @throws java.io.IOException
     */
    List<CurrencyDto> retrieveCurrencies() throws IOException, UnirestException;
}
