package com.clearkode.exchange.ratesapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@RequiredArgsConstructor
public class RatesApiResponseErrorHandler extends DefaultResponseErrorHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String body = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());

        String message = null;
        try {
            if (!StringUtils.isEmpty(body)) {
                Error error = objectMapper.readValue(response.getBody(), Error.class);
                message = error.getMessage();
            }
        } catch (Exception ex) {
        }
        throw new RatesApiException(response.getStatusCode(), message);
    }
}
