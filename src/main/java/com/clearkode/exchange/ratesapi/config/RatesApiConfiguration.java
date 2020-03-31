package com.clearkode.exchange.ratesapi.config;

import com.clearkode.exchange.service.RatesApiService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.Collections;

@Slf4j
@Configuration
@ConditionalOnMissingBean(RatesApiService.class)
@EnableConfigurationProperties(RatesApiProperties.class)
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class RatesApiConfiguration {

    private final RatesApiProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public RatesApiService ratesApiService(RestTemplateBuilder restTemplateBuilder) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory());

        RestTemplate restTemplate = restTemplateBuilder
                .rootUri(properties.getUrl())
                .build();

        restTemplate.setRequestFactory(factory);
        restTemplate.setMessageConverters(Collections.singletonList(converter));
        restTemplate.setInterceptors(Collections.singletonList(new RatesApiHttpRequestInterceptor()));
        restTemplate.setErrorHandler(new RatesApiResponseErrorHandler(objectMapper));

        return new RatesApiService(restTemplate, objectMapper);
    }
}
