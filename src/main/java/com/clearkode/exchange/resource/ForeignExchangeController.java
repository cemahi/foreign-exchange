package com.clearkode.exchange.resource;

import com.clearkode.exchange.handler.common.TraceableHandlerService;
import com.clearkode.exchange.handler.conversion.ListConversionHandler;
import com.clearkode.exchange.handler.conversion.MakeConversionHandler;
import com.clearkode.exchange.ratesapi.request.ListConversionRequest;
import com.clearkode.exchange.ratesapi.request.MakeConversionRequest;
import com.clearkode.exchange.ratesapi.response.ListConversionResponse;
import com.clearkode.exchange.ratesapi.response.MakeConversionResponse;
import com.clearkode.exchange.service.RatesApiService;
import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;
import com.clearkode.exchange.ratesapi.response.ExchangeCurrencyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class ForeignExchangeController {

    private final RatesApiService ratesApiService;
    private final TraceableHandlerService serviceExecuter;
    private final MakeConversionHandler makeConversionHandler;
    private final ListConversionHandler listConversionHandler;

    @PostMapping("/rate")
    public ResponseEntity<ExchangeCurrencyResponse> getConversionRate(@Valid @RequestBody ExchangeCurrencyRequest request) {
        return ResponseEntity.ok(ratesApiService.getConversionRate(request));
    }

    @PostMapping("/conversion")
    public ResponseEntity<MakeConversionResponse> makeConversion(@Valid @RequestBody MakeConversionRequest request) {
        return ResponseEntity.ok((MakeConversionResponse)serviceExecuter.execute(makeConversionHandler, request));
    }

    @GetMapping("/conversion")
    public ResponseEntity<ListConversionResponse> getConversions(Pageable pageable,
                                                                 @RequestParam(value = "bankAccountStatus", required = false) String bankAccountStatus) {
        ListConversionRequest request = ListConversionRequest.create();
        return ResponseEntity.ok((ListConversionResponse)serviceExecuter.execute(listConversionHandler, request));
    }
}