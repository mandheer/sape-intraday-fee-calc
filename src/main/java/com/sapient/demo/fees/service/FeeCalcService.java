package com.sapient.demo.fees.service;

import com.sapient.demo.exception.FeeCalcTransException;
import com.sapient.demo.fees.cache.EquityCacheManager;
import com.sapient.demo.fees.data.*;
import com.sapient.demo.fees.fare.FeeCalculatorStrategyFactory;
import com.sapient.demo.fees.fare.TransactionFare;
import com.sapient.demo.logger.TransLogMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeeCalcService implements FeeCalcServiceProvider{

    public static Logger LOG = LoggerFactory.getLogger(FeeCalcService.class);

    @Autowired
    public FeeCalculatorStrategyFactory feeCalculatorStrategyFactory;

    @Autowired
    EquityCacheManager<FeeCalcDetails> feeCalcDetailsCache;


    public double calculateFare(TransactionDetails detail){
        return calculateFare(detail, getTransactionType(detail));
    }

    public double calculateFare(TransactionDetails detail, TransactionFare fare){
        return getTransactionType(detail) != null ?
                feeCalculatorStrategyFactory.getFeeCalculatorStrategy(fare).calculateTransactionFare() :
                0.0 ;
    }

    private TransactionFare getTransactionType(TransactionDetails detail){
        //if(isIntraDay(detail)) return TransactionFare.BASIC;
        if(TransactionPriority.Y.equals(detail.getPriority())) return TransactionFare.PREMIUM;
        if(TransactionPriority.N.equals(detail.getPriority())) {
            if (TransactionType.SELL.equals(detail.getType()) || TransactionType.WITHDRAW.equals(detail.getType())){
                return TransactionFare.NOMINAL;
            } else if (TransactionType.BUY.equals(detail.getType()) || TransactionType.DEPOSIT.equals(detail.getType())){
                return TransactionFare.DISCOUNTED;
            }
        }
        return null;

    }

   /* private boolean isIntraDay(TransactionDetails transactionDetails) {
        LOG.trace(TransLogMessage.TRANS00001T,"isIntraDay()");
        return transactionDetailsCache.cache(transactionDetails) == null ?
                false : true;

    }*/

    @Override
    public FeeCalcDetails process(TransactionDetails detail) throws FeeCalcTransException {
        FeeCalcDetails item = responseMapper(detail);
        FeeCalcDetails item2  = feeCalcDetailsCache.cache(item);
        if(item2 != null) {//item added to cache
            double fare = calculateFare(detail,TransactionFare.BASIC);
            item.setProcessingFee(fare);
            item2.setProcessingFee(fare);
        }
        //callback will print the netire cache.
        return null;
    }

    private FeeCalcDetails responseMapper(TransactionDetails item){
        FeeCalcDetails response = new FeeCalcDetails();
        response.setClientId(item.getClientId());
        response.setSecurityId(item.getSecurityId());
        response.setPriority(item.getPriority());
        response.setTransDate(item.getTransDate());
        response.setType(item.getType());
        response.setProcessingFee(calculateFare(item));
        return response;
    }
}
