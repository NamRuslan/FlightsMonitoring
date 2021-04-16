package com.namruslan.flightsmonitoring.flightsmonitoring.service;

import com.namruslan.flightsmonitoring.flightsmonitoring.database.entities.Subscription;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.FlightPricesDto;

/**
 * Service for getting details based on {@link Subscription} object
 */
public interface FlightPriceService {

    /**
     * Finds min price based on {@link Subscription}
     *
     * @param flightPricesDto provided {@link FlightPricesDto} object
     */
    Integer findMinPrice(FlightPricesDto flightPricesDto);

    /**
     * Finds all flight data related to {@link Subscription} object
     *
     * @param subscription provided {@link Subscription} object
     * @return {@link FlightPricesDto} with all data related to flight specific in {@link Subscription}
     */
    FlightPricesDto findFlightPrice(Subscription subscription);
}
