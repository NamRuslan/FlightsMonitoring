package com.namruslan.flightsmonitoring.flightsmonitoring.service.impl;

import com.namruslan.flightsmonitoring.flightsmonitoring.database.entities.Subscription;
import com.namruslan.flightsmonitoring.flightsmonitoring.service.EmailNotifierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class EmailNotifierServiceImpl implements EmailNotifierService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifySubscriber(Subscription subscription, Integer oldMinPrice, Integer newMinPrice) {
        log.debug("method notifySubscriber STARTED");

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(subscription.getEmail());
        msg.setSubject("Flights Monitoring Service");
        msg.setText(String.format(
                "Hello, dear!\n" +
                        "The price for your flight has decreased\n" +
                        "Old min price = %s.\n" +
                        "New min price = %s.\n" +
                        "Subscription deatails: %s",
                oldMinPrice, newMinPrice, subscription.toString()
        ));

        javaMailSender.send(msg);
        log.debug("method notifySubscriber FINISHED");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyAddingSubscription(Subscription subscription) {

        log.debug("method notifyAddingSubscriber STARTED");

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(subscription.getEmail());
        msg.setSubject("Flights Monitoring Service");
        msg.setText(String.format(
                "Hello, dear!\n" +
                        "Subscription has been successfully added.\n" +
                        "Subscription details: %s",
                subscription.toString()
        ));

        javaMailSender.send(msg);
        log.debug("method notifyAddingSubscriber FINISHED");
    }
}
