package com.clearkode.exchange.resource;

import com.clearkode.exchange.ratesapi.RatesApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/v1/rates")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class ExchangeController {

    private final RatesApiService ratesApiService;

    @PostMapping()
    public void getConversionRate(@Valid @RequestBody String request) {

         ratesApiService.getConversionRate();
    }
}