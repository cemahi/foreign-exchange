package com.clearkode.exchange;

import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;
import com.clearkode.exchange.ratesapi.response.ExchangeCurrencyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;

@AutoConfigureWebClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForeignExchangeControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private String baseUrl = "/v1";
    private HttpHeaders headers;
    private HttpEntity<String> entity;

    @Before
    public void Init() {
        headers = getHeader();
    }

    @Test()
    public void getConversionRate_when_ServerRespondsWithRate_ExchangeRateReturned() throws JsonProcessingException {
        ExchangeCurrencyRequest request = new ExchangeCurrencyRequest();
        request.setTarget(Currency.getInstance("USD"));
        request.setSource(Currency.getInstance("GBP"));

        String requestJson = mapper.writeValueAsString(request);

		entity = new HttpEntity<>(requestJson, headers);
		ResponseEntity<ExchangeCurrencyResponse> response = restTemplate.exchange(baseUrl + "/rate",
				HttpMethod.POST, entity, ExchangeCurrencyResponse.class);

		Assert.assertSame(HttpStatus.OK, response.getStatusCode());
    }

    private HttpHeaders getHeader() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
