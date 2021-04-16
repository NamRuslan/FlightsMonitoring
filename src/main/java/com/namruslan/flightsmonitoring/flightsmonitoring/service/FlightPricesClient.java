package com.namruslan.flightsmonitoring.flightsmonitoring.service;

import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.FlightPricesDto;

/**
 * Browse flight prices
 */
public interface FlightPricesClient {

    /**
     * Browse quotes for current flight based on args. One-way ticket.
     *
     * @param country country from
     * @param currency price currency
     * @param locale locale for response
     * @param originPlace origin place
     * @param destinationPlace destination place
     * @param outboundPartialDate outbound date
     * @return {@link FlightPricesDto} object
     */
    FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace,
                                 String destinationPlace, String outboundPartialDate);

    /**
     * Browse quotes for current flight based on args. Round trip ticket.
     *
     * @param country country from
     * @param currency price currency
     * @param locale locale for response
     * @param originPlace origin place
     * @param destinationPlace destination place
     * @param outboundPartialDate outbound date
     * @param inboundPartialDate inbound date
     * @return {@link FlightPricesDto} object
     */
    FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace,
                                 String destinationPlace, String outboundPartialDate, String inboundPartialDate);

}
