package com.clearkode.exchange.handler.conversion;

import com.clearkode.exchange.entity.common.CommonAppException;
import com.clearkode.exchange.entity.common.ErrorType;
import com.clearkode.exchange.entity.transaction.Transaction;
import com.clearkode.exchange.entity.transaction.TransactionSpec;
import com.clearkode.exchange.handler.common.BaseHandler;
import com.clearkode.exchange.handler.common.Handler;
import com.clearkode.exchange.ratesapi.request.ListConversionRequest;
import com.clearkode.exchange.ratesapi.response.ConversionResponse;
import com.clearkode.exchange.ratesapi.response.ListConversionResponse;
import com.clearkode.exchange.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
@Transactional
public class ListConversionHandler extends BaseHandler implements Handler<ListConversionRequest, ListConversionResponse> {

    private final TransactionRepository transactionRepository;

    @Override
    @Transactional(readOnly = true)
    public ListConversionResponse execute(ListConversionRequest request) {
        ListConversionResponse response = new ListConversionResponse();

        List<Specification<Transaction>> specificationList = new ArrayList<>();
        Specification<Transaction> specifications = null;

        if (null != request.getStartTransactionDate())
            specificationList.add(TransactionSpec.start(request.getStartTransactionDate()));

        if (null != request.getEndTransactionDate())
            specificationList.add(TransactionSpec.start(request.getEndTransactionDate()));

        if (null != request.getTransactionId())
            specificationList.add(TransactionSpec.transactionId(request.getTransactionId()));

        if(specificationList.isEmpty()) {
            throw new CommonAppException(ErrorType.AT_LEAST_ONE_ELEMENT_HAS_TO_SELECTED);
        }
        for (Specification<Transaction> specification : specificationList) {
            specifications = Objects.nonNull(specifications) ? specifications.and(specification) : Specification.where(specification);
        }

        Page<Transaction> transactions = transactionRepository.findAll(specifications, request.getPageable());
        Page<ConversionResponse> conversionResponses = transactions.map(ConversionResponse::create);
        response.setConversions(conversionResponses);

        return response;
    }
}
