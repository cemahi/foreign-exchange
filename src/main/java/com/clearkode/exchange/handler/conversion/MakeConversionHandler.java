package com.clearkode.exchange.handler.conversion;

import com.clearkode.exchange.handler.common.BaseHandler;
import com.clearkode.exchange.handler.common.Handler;
import com.clearkode.exchange.ratesapi.request.MakeConversionRequest;
import com.clearkode.exchange.ratesapi.response.MakeConversionResponse;
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
public class MakeConversionHandler extends BaseHandler implements Handler<MakeConversionRequest, MakeConversionResponse> {

    @Override
    public MakeConversionResponse execute(MakeConversionRequest request) {
        return null;
    }
}
