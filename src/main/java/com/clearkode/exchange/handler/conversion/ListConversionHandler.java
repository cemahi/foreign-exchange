package com.clearkode.exchange.handler.conversion;

import com.clearkode.exchange.handler.common.BaseHandler;
import com.clearkode.exchange.handler.common.Handler;
import com.clearkode.exchange.ratesapi.request.ListConversionRequest;
import com.clearkode.exchange.ratesapi.response.ListConversionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
@Transactional
public class ListConversionHandler extends BaseHandler implements Handler<ListConversionRequest, ListConversionResponse> {

    @Override
    public ListConversionResponse execute(ListConversionRequest request) {
        return null;
    }
}
