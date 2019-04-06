package com.sapient.demo.fees.fare;

import org.springframework.stereotype.Service;

@Service
public class PremiumFareCalculator implements FeeCalculatorStrategy {
    @Override
    public double calculateTransactionFare() {
        return 500;
    }
}
