package com.namruslan.flightsmonitoring.flightsmonitoring.database.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "subscription")
public class Subscription implements Serializable {

    private static final long serialUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "currency")
    private String currency;

    @Column(name = "locale")
    private String locale;

    @Column(name = "origin_place")
    private String originPlace;

    @Column(name = "destination_place")
    private String destinationPlace;

    @Column(name = "outbound_partial_date")
    private LocalDate outboundPartialDate;

    @Column(name = "inbound_partial_date")
    private LocalDate inboundPartialDate;

    @Column(name = "min_price")
    private Integer minPrice;


}
