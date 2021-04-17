package com.namruslan.flightsmonitoring.flightsmonitoring.scheduling;

import com.namruslan.flightsmonitoring.flightsmonitoring.database.repo.SubscriptionRepo;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.FlightPricesDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.EmailNotifierService;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.FlightPriceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * {@inheritDoc}
 */
public class RecountMinPriceServiceImpl implements RecountMinPriceService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private FlightPriceService flightPriceService;

    @Autowired
    private EmailNotifierService emailNotifierService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void recount() {
        subscriptionRepo.findAll().forEach(subscription -> {

            if (subscription.getOutboundPartialDate().isAfter(LocalDate.now().minusDays(1))) {
                FlightPricesDto flightPricesDto = flightPriceService.findFlightPrice(subscription);
                Integer newNumPrice = flightPriceService.findMinPrice(flightPricesDto);

                if (subscription.getMinPrice() > newNumPrice) {
                    emailNotifierService.notifySubscriber(subscription, subscription.getMinPrice(), newNumPrice);
                    subscription.setMinPrice(newNumPrice);
                    subscriptionRepo.save(subscription);
                }
            } else {
                subscriptionRepo.delete(subscription);
            }
        });
    }
}
