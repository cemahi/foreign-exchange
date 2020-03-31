package com.clearkode.exchange.ratesapi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateResponse {
    private String base;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
