package com.sapient.demo.fees.fare;

import org.springframework.beans.factory.annotation.Autowired;

public class TransFareCalculator {

    @Autowired
    public FeeCalculatorStrategyFactory feeCalculatorStrategyFactory;

    //Fares are fixed for specific transactions
    public double calculateFare(TransactionFare type){
        return feeCalculatorStrategyFactory.getFeeCalculatorStrategy(type) == null ? 0.0 :
                feeCalculatorStrategyFactory.getFeeCalculatorStrategy(type).calculateTransactionFare();

    }
}
