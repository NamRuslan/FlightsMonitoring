package com.namruslan.flightsmonitoring.flightsmonitoring.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public interface UniRestService {

    /**
     * Create GET request based on provided {@param path} with needed headers
     *
     * @param path provided path with all the needed data
     * @return {@link HttpResponse<JsonNode>} response object
     */
    HttpResponse<JsonNode> get(String path);
}
