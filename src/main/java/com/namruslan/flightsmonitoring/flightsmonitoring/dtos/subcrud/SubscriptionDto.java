package com.namruslan.flightsmonitoring.flightsmonitoring.dtos.subcrud;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.namruslan.flightsmonitoring.flightsmonitoring.dtos.FlightPricesDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * DTO to see all data related to subscription
 */
@Data
public class SubscriptionDto {

    private Long id;

    @NotNull
    @Email
    @ApiModelProperty(value = "Subscriber's email", example = "test@test.com")
    private String email;

    @NotNull
    @ApiModelProperty(value = "Country Code", example = "UA")
    private String country;

    @NotNull
    @ApiModelProperty(value = "Currency Code", example = "UAH")
    private String currency;

    @NotNull
    @ApiModelProperty(value = "Locale", example = "ru-RU")
    private String locale;

    @NotNull
    @ApiModelProperty(value = "Code of the origin place", example = "HRK-sky")
    private String originPlace;

    @NotNull
    @ApiModelProperty(value = "Code of the destination place", example = "KBP-sky")
    private String destinationPlace;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Date front", example = "2021-04-17")
    private LocalDate outboundPartialDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Date back", example = "2021-05-24")
    private LocalDate inboundPartialDate;

    @ApiModelProperty(value = "Min price based on all these data", example = "100")
    private Integer minPrice;

    @ApiModelProperty(value = "Response which contains all needed info about min price flight")
    private FlightPricesDto flightPricesDto;


}
