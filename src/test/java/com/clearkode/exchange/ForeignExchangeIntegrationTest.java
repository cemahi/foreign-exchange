package com.clearkode.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;


@AutoConfigureWebClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForeignExchangeIntegrationTest{

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private String baseUrl = "/v1";
    private HttpHeaders headers;
    private HttpEntity<String> entity;

    @Before
    public void Init() {
    }

    @Test
    public void Test() {
    }
}
