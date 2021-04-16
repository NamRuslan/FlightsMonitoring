package com.namruslan.flightsmonitoring.flightsmonitoring.service;

import com.namruslan.flightsmonitoring.flightsmonitoring.database.entities.Subscription;

/**
 * Sends email notification
 */
public interface EmailNotifierService {

    /**
     * Notifies subscriber, that the min price has decreased
     *
     * @param subscription {@link Subscription} object
     * @param oldMinPrice min price before recount
     * @param newMinPrice min price after recount
     */
    void notifySubscriber(Subscription subscription, Integer oldMinPrice, Integer newMinPrice);

    /**
     * Notifies subscriber, that subscription has added
     *
     * @param subscription {@link Subscription} object
     */
    void notifyAddingSubscription(Subscription subscription);
}
