package com.sapient.demo.fees.fare;

import org.springframework.stereotype.Service;

@Service
public class DiscountedCalculator implements FeeCalculatorStrategy {
    @Override
    public double calculateTransactionFare() {
        return 50.0;
    }
}
