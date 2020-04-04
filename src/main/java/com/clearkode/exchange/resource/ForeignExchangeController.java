package com.clearkode.exchange.resource;

import com.clearkode.exchange.handler.common.TraceableHandlerService;
import com.clearkode.exchange.handler.conversion.ConversionRateHandler;
import com.clearkode.exchange.handler.conversion.ListConversionHandler;
import com.clearkode.exchange.handler.conversion.MakeConversionHandler;
import com.clearkode.exchange.resource.request.ConversionRateRequest;
import com.clearkode.exchange.resource.request.ListConversionRequest;
import com.clearkode.exchange.resource.request.MakeConversionRequest;
import com.clearkode.exchange.resource.response.ConversionRateResponse;
import com.clearkode.exchange.resource.response.ListConversionResponse;
import com.clearkode.exchange.resource.response.MakeConversionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class ForeignExchangeController {

    private final TraceableHandlerService serviceExecuter;
    private final MakeConversionHandler makeConversionHandler;
    private final ListConversionHandler listConversionHandler;
    private final ConversionRateHandler conversionRateHandler;

    @PostMapping("/rate")
    public ResponseEntity<ConversionRateResponse> getConversionRate(@Valid @RequestBody ConversionRateRequest request) {
        return ResponseEntity.ok((ConversionRateResponse)serviceExecuter.execute(conversionRateHandler, request));
    }

    @PostMapping("/conversion")
    public ResponseEntity<MakeConversionResponse> makeConversion(@Valid @RequestBody MakeConversionRequest request) {
        return ResponseEntity.ok((MakeConversionResponse)serviceExecuter.execute(makeConversionHandler, request));
    }

    @GetMapping("/conversion")
    public ResponseEntity<ListConversionResponse> getConversions(Pageable pageable,
                                                                 @RequestParam(value = "transactionId", required = false) UUID transactionId,
                                                                 @RequestParam(value = "startTransactionDate", required = false)
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTransactionDate,
                                                                 @RequestParam(value = "startTransactionDate", required = false)
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTransactionDate) {
        ListConversionRequest request = ListConversionRequest.create(transactionId, startTransactionDate, endTransactionDate, pageable);
        return ResponseEntity.ok((ListConversionResponse)serviceExecuter.execute(listConversionHandler, request));
    }
}