package com.sapient.demo.fees.fare;

import org.springframework.beans.factory.annotation.Autowired;

@TransFeeFactory
public class FeeCalculatorStrategyFactory {

    @Autowired
    private PremiumFareCalculator premiumFareCalculator;

    @Autowired
    private NominalFareCalculator nominalFareCalculator;

    @Autowired
    private DiscountedCalculator discountedCalculator;

    @Autowired
    private BasicFareCalculator basicFareCalculator;

    public FeeCalculatorStrategy getFeeCalculatorStrategy(TransactionFare type){
        switch(type){
            case PREMIUM: return premiumFareCalculator;
            case NOMINAL: return nominalFareCalculator;
            case DISCOUNTED: return discountedCalculator;
            case BASIC: return basicFareCalculator;
            default : return null;
        }
    }
}
