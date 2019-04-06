package com.sapient.demo.fees.fare;

import org.springframework.stereotype.Service;

@Service
public class BasicFareCalculator implements FeeCalculatorStrategy {
    @Override
    public double calculateTransactionFare() {
        return 10.0;
    }
}
