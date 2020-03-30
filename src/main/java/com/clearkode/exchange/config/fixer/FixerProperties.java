package com.clearkode.exchange.config.fixer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "fixer")
@Data
@Validated
public class FixerProperties {

    @NotNull
    private String url;

    @NotNull
    private String apiKey;

}