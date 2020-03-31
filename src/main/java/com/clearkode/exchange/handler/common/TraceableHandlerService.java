package com.clearkode.exchange.handler.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class TraceableHandlerService {

    public BaseResponse execute(Handler handler, BaseRequest request) {
        BaseResponse executed = (BaseResponse) handler.execute(request);
        executed.setRequestId(request.getRequestId());
        return executed;
    }
}
