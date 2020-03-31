package com.clearkode.exchange.ratesapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "ratesapi")
@Data
@Validated
public class RatesApiProperties {

    @NotNull
    private String url;

}