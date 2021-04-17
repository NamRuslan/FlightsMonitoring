package com.namruslan.flightsmonitoring.flightsmonitoring.controller;

import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud.SubscriptionCreateDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud.SubscriptionDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud.SubscriptionUpdateDto;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * {@link Controller} to handle Subscriptions
 */
@Api(value = "Operations with subscriptions", tags = "Subscription Controller")
@RequestMapping(SubscriptionController.SUBSCRIPTION_CONTROLLER_EP)
@Controller
public class SubscriptionController {

    public static final String SUBSCRIPTION_CONTROLLER_EP = "/subscription";

    @Autowired
    private SubscriptionService subscriptionService;

    @ApiOperation("Create new subscription based in SubscriptionDto")
    @PostMapping
    public @ResponseBody
    SubscriptionDto create(@RequestBody @Valid SubscriptionCreateDto dto) {
        return subscriptionService.create(dto);
    }

    @ApiOperation("Find all subscription by email")
    @GetMapping("/{email}")
    public @ResponseBody
    List<SubscriptionDto> findByEmail(@PathVariable final String email) {
        return subscriptionService.findByEmail(email);
    }

    @ApiOperation("Update subscription by ID")
    @PutMapping("/{id}")
    public SubscriptionDto update(@PathVariable final Long id, @RequestBody @Valid SubscriptionUpdateDto dto) {
        return subscriptionService.update(id, dto);
    }

    @ApiOperation("Delete subscription by ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        subscriptionService.delete(id);
    }
}
