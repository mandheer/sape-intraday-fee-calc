package com.sapient.demo.fees.fare;

import org.springframework.stereotype.Service;

@Service
public class NominalFareCalculator implements FeeCalculatorStrategy {
    @Override
    public double calculateTransactionFare() {
        return 100.0;
    }
}
