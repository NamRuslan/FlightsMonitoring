package com.namruslan.flightsmonitoring.flightsmonitoring.service.impl;

import com.namruslan.flightsmonitoring.flightsmonitoring.database.entities.Subscription;
import com.namruslan.flightsmonitoring.flightsmonitoring.database.repo.SubscriptionRepo;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.FlightPricesDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud.SubscriptionCreateDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud.SubscriptionDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud.SubscriptionUpdateDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.EmailNotifierService;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.FlightPriceService;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private FlightPriceService flightPriceService;

    @Autowired
    private EmailNotifierService emailNotifierService;

    private Subscription toEntity(SubscriptionCreateDto dto) {

        Subscription subscription = new Subscription();
        subscription.setEmail(dto.getEmail());
        subscription.setCountry(dto.getCountry());
        subscription.setCurrency(dto.getCurrency());
        subscription.setLocale(dto.getLocale());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());

        return subscription;
    }

    private SubscriptionDto toDto(Subscription entity, FlightPricesDto response) {
        SubscriptionDto dto = new SubscriptionDto();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setCountry(entity.getCountry());
        dto.setCurrency(entity.getCurrency());
        dto.setLocale(entity.getLocale());
        dto.setOriginPlace(entity.getOriginPlace());
        dto.setDestinationPlace(entity.getDestinationPlace());
        dto.setOutboundPartialDate(entity.getOutboundPartialDate());
        dto.setInboundPartialDate(entity.getInboundPartialDate());
        dto.setMinPrice(entity.getMinPrice());
        dto.setFlightPricesDto(response);

        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDto create(SubscriptionCreateDto dto) {
        Subscription subscription = toEntity(dto);
        Optional<Subscription> one = subscriptionRepo.findOne(Example.of(subscription));

        if (one.isPresent()) {
            log.info("Subscription={} is already exist.", subscription);
            Subscription fromDatabase = one.get();
            FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
            subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesResponse));

            return toDto(fromDatabase, flightPricesResponse);
        } else {
            FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
            subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesResponse));
            Subscription saved = subscriptionRepo.save(subscription);
            log.info("Added new subscription={}", saved);
            emailNotifierService.notifyAddingSubscription(subscription);

            return toDto(saved, flightPricesResponse);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubscriptionDto> findByEmail(String email) {
        return subscriptionRepo.findByEmail(email).stream()
                .map(subscription -> {
                    FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
                    if (subscription.getMinPrice() != flightPriceService.findMinPrice(flightPricesResponse)) {
                        subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesResponse));
                        subscriptionRepo.save(subscription);
                    }
                    return toDto(subscription, flightPricesResponse);
                })
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long subscriptionId) {
        subscriptionRepo.deleteById(subscriptionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDto update(Long subscriptionId, SubscriptionUpdateDto dto) {
        Subscription subscription = subscriptionRepo.getOne(subscriptionId);

        subscription.setEmail(dto.getEmail());
        subscription.setCountry(dto.getCountry());
        subscription.setCurrency(dto.getCurrency());
        subscription.setLocale(dto.getLocale());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());

        FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
        subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesResponse));

        return toDto(subscriptionRepo.save(subscription), flightPricesResponse);
    }
}
