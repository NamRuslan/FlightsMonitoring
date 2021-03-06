package com.namruslan.flightsmonitoring.flightsmonitoring.service.impl;

import com.namruslan.flightsmonitoring.flightsmonitoring.database.entities.Subscription;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.FlightPricesDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.FlightPriceService;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.FlightPricesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class FlightPriceServiceImpl implements FlightPriceService {

    @Autowired
    private FlightPricesClient flightPricesClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer findMinPrice(FlightPricesDto flightPricesDto) {
        return flightPricesDto.getQuotes().get(0).getMinPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlightPricesDto findFlightPrice(Subscription subscription) {

        if (subscription.getInboundPartialDate() == null) {
            return flightPricesClient
                    .browseQuotes(subscription.getCountry(), subscription.getCurrency(),
                            subscription.getLocale(), subscription.getOriginPlace(),
                            subscription.getDestinationPlace(), subscription.getOutboundPartialDate().toString());
        } else {
            return flightPricesClient
                    .browseQuotes(subscription.getCountry(), subscription.getCurrency(),
                            subscription.getLocale(), subscription.getOriginPlace(),
                            subscription.getDestinationPlace(), subscription.getOutboundPartialDate().toString(),
                            subscription.getInboundPartialDate().toString());
        }
    }
}
