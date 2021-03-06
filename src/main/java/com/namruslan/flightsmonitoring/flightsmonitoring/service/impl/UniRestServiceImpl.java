package com.namruslan.flightsmonitoring.flightsmonitoring.service.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.namruslan.flightsmonitoring.flightsmonitoring.exception.FlightClientException;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.UniRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UniRestServiceImpl implements UniRestService {

    public static final String HOST = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";

    public static final String PLACES_FORMAT = "/apiservices/autosuggest/v1.0/%s/%s/%s/?query=%s";
    public static final String CURRENCIES_FORMAT = "/apiservices/reference/v1.0/currencies";
    public static final String COUNTRIES_FORMAT = "/apiservices/reference/v1.0/countries/%s";

    public static final String PLACES_KEY = "Places";
    public static final String CURRENCIES_KEY = "Currencies";
    public static final String COUNTRIES_KEY = "Countries";

    @Value("${x.rapid.api.key}")
    private String xRapidApiKey;


    @Override
    public HttpResponse<JsonNode> get(String path) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(HOST + path)
                    .header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                    .header("x-rapidapi-key", xRapidApiKey)
                    .asJson();
        } catch (UnirestException e) {
            throw new FlightClientException(String.format("Request failed, path = %s", HOST + path), e);
        }

        log.info("Response from GET request, on path = {}, statusCode = {}, response = {}",
                path, response.getStatus(), response.getBody().toString());

        return response;
    }
}
