package com.clearkode.exchange.handler.conversion;

import com.clearkode.exchange.config.ProviderFactory;
import com.clearkode.exchange.config.ProviderType;
import com.clearkode.exchange.entity.transaction.Transaction;
import com.clearkode.exchange.entity.common.OperationType;
import com.clearkode.exchange.handler.common.BaseHandler;
import com.clearkode.exchange.handler.common.Handler;
import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;
import com.clearkode.exchange.resource.request.MakeConversionRequest;
import com.clearkode.exchange.resource.response.MakeConversionResponse;
import com.clearkode.exchange.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
@Transactional
public class MakeConversionHandler extends BaseHandler
        implements Handler<MakeConversionRequest, MakeConversionResponse> {

    private final ProviderFactory providerFactory;
    private final TransactionRepository transactionRepository;

    @Override
    public MakeConversionResponse execute(MakeConversionRequest request) {
        ExchangeCurrencyRequest exchangeCurrencyRequest = new ExchangeCurrencyRequest();
        exchangeCurrencyRequest.setSource(request.getSourceCurrency());
        exchangeCurrencyRequest.setTarget(request.getTargetCurrency());

        BigDecimal rate = providerFactory.getProvider(ProviderType.RATESAPI).getConversionRate(exchangeCurrencyRequest);
        BigDecimal targetAmount = request.getSourceAmount().multiply(rate);
        Transaction transaction = Transaction.Create(request.getSourceAmount(),targetAmount, request.getSourceCurrency(),
                request.getTargetCurrency(), OperationType.CONVERSION);
        transactionRepository.save(transaction);
        return new MakeConversionResponse(transaction.getId(), targetAmount);
    }
}
