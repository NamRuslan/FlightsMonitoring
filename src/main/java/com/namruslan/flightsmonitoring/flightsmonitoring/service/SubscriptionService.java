package com.namruslan.flightsmonitoring.flightsmonitoring.service;

import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud.SubscriptionCreateDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud.SubscriptionDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud.SubscriptionUpdateDto;

import java.util.List;

/**
 * Subscription management
 */
public interface SubscriptionService {

    /**
     * Add new subscription
     *
     * @param dto DTO of the subscription
     */
    SubscriptionDto create(SubscriptionCreateDto dto);

    /**
     * Get all subscriptions based on email
     *
     * @param email email of the subscriber
     * @return collection of {@link SubscriptionDto} objects
     */
    List<SubscriptionDto> findByEmail(String email);

    /**
     * Deletes subscription by id
     *
     * @param subscriptionId ID of the subscription
     */
    void delete(Long subscriptionId);

    /**
     * Update subscription by ID
     *
     * @param subscriptionId ID of the subscription
     * @param dto the data to be updated
     * @return updated {@link SubscriptionDto} object
     */
    SubscriptionDto update(Long subscriptionId, SubscriptionUpdateDto dto);
}
