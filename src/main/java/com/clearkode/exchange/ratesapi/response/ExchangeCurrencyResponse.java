package com.clearkode.exchange.ratesapi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeCurrencyResponse {
    private String base;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Map<String, String> rates;
}
