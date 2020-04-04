package com.clearkode.exchange;

import com.clearkode.exchange.resource.request.ConversionRateRequest;
import com.clearkode.exchange.resource.response.ConversionRateResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForeignExchangeControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private HttpHeaders headers;
    private HttpEntity<String> entity;

    @Before
    public void Init() {
        headers = getHeader();
    }

    @Test()
    public void getConversionRate_when_ServerRespondsWithRate_ExchangeRateReturned() throws JsonProcessingException {
        ConversionRateRequest request = new ConversionRateRequest();
        request.setTarget(Currency.getInstance("USD"));
        request.setSource(Currency.getInstance("GBP"));

        String requestJson = mapper.writeValueAsString(request);

		entity = new HttpEntity<>(requestJson, headers);
		ResponseEntity<ConversionRateResponse> response = restTemplate.exchange("/v1/rate",
				HttpMethod.POST, entity, ConversionRateResponse.class);

		Assert.assertSame(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(Objects.requireNonNull(response.getBody()).getRate());
    }

    private HttpHeaders getHeader() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
