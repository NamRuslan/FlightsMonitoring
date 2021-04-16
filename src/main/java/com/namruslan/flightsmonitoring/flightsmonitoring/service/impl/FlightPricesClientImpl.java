package com.namruslan.flightsmonitoring.flightsmonitoring.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.*;
import com.namruslan.flightsmonitoring.flightsmonitoring.exception.FlightClientException;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.FlightPricesClient;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.UniRestService;
import com.namruslan.flightsmonitoring.flightsmonitoring.validation.ValidationErrorDto;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.namruslan.flightsmonitoring.flightsmonitoring.service.impl.UniRestServiceImpl.CURRENCIES_KEY;
import static com.namruslan.flightsmonitoring.flightsmonitoring.service.impl.UniRestServiceImpl.PLACES_KEY;

/**
 * {@inheritDoc}
 */
@Service
public class FlightPricesClientImpl implements FlightPricesClient {

    public static final String BROWSE_QUOTES_FORMAT = "/apiservices/browsequotes/v1.0/%s/%s/%s/%s/%s/%s";
    public static final String OPTIONAL_BROWSE_QUOTES_FORMAT = BROWSE_QUOTES_FORMAT + "?inboundpartialdate=%s";

    public static final String QUOTES_KEY = "Quotes";
    public static final String ROUTES_KEY = "Routes";
    public static final String DATES_KEY = "Dates";
    public static final String CARRIERS_KEY = "Carriers";
    public static final String VALIDATIONS_KEY = "ValidationErrors";

    @Autowired
    private UniRestService uniRestService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace,
                                        String destinationPlace, String outboundPartialDate) {

        HttpResponse<JsonNode> response = uniRestService.get(String.format(
                BROWSE_QUOTES_FORMAT, country, currency, locale, originPlace, destinationPlace, outboundPartialDate));

        return mapToObject(response);
    }

    @Override
    public FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace,
                                        String destinationPlace, String outboundPartialDate, String inboundPartialDate) {

        HttpResponse<JsonNode> response = uniRestService.get(String.format(
                OPTIONAL_BROWSE_QUOTES_FORMAT, country, currency, locale, originPlace, destinationPlace,
                outboundPartialDate, inboundPartialDate));

        return mapToObject(response);
    }

    private FlightPricesDto mapToObject(HttpResponse<JsonNode> response) {

        if (response.getStatus() == HttpStatus.SC_OK) {

            FlightPricesDto flightPricesDto = new FlightPricesDto();

            flightPricesDto.setCarriers(readValue(response.getBody().getObject().get(CARRIERS_KEY).toString(),
                    new TypeReference<List<CarriersDto>>(){
                    }));
            flightPricesDto.setCurrencies(readValue(response.getBody().getObject().get(CURRENCIES_KEY).toString(),
                    new TypeReference<List<CurrencyDto>>() {
                    }));
            flightPricesDto.setPlaces(readValue(response.getBody().getObject().get(PLACES_KEY).toString(),
                    new TypeReference<List<BrowsePlacesDto>>() {
                    }));
            flightPricesDto.setQuotes(readValue(response.getBody().getObject().get(QUOTES_KEY).toString(),
                    new TypeReference<List<QuotesDto>>() {
                    }));
            return flightPricesDto;
        }
        throw new FlightClientException(String.format("There are validation errors. Status code = %s", response.getStatus()),
                readValue(response.getBody().getObject().get(VALIDATIONS_KEY).toString(),
                        new TypeReference<List<ValidationErrorDto>>() {
                        }));
    }

    private <T> List<T> readValue(String resultAsString, TypeReference<List<T>> valueTypeRef) {
        List<T> list;
        try {
            list = objectMapper.readValue(resultAsString, valueTypeRef);
        } catch (IOException e) {
            throw new FlightClientException("Object Mapping failure.", e);
        }
        return list;
    }
}
