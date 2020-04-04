package com.clearkode.exchange.config;

import com.clearkode.exchange.entity.common.CommonAppException;
import com.clearkode.exchange.entity.common.ErrorType;
import com.clearkode.exchange.service.RatesApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
@Service
public class ProviderFactory {

    private final RatesApiService ratesApiService;

    public IApiService getProvider(ProviderType providerType){
        if(providerType == ProviderType.RATESAPI){
            return ratesApiService;
        }
        throw new CommonAppException(ErrorType.PROVIDER_NOT_FOUND);
    }
}
