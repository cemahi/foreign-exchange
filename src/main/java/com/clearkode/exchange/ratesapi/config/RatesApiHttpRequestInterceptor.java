package com.clearkode.exchange.ratesapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@RequiredArgsConstructor
public class RatesApiHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        if (log.isInfoEnabled()) {
            log.info("[Request] [{}]  {}, Headers : [{}], Body : [{}]", request.getMethod(), request.getURI(), request.getHeaders(), new String(body, Charset.defaultCharset()));
        }

        ClientHttpResponse response = execution.execute(request, body);

        String respBody = null;
        try {
            if (null != response.getBody())
                respBody = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
        } catch (Exception ex) {
        }

        if (log.isInfoEnabled()) {
            log.error("[Response] [{}], Headers : [{}], Body : [{}] ", response.getStatusCode(), response.getHeaders(), respBody);
        }

        return response;
    }
}
