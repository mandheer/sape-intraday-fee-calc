package com.sapient.demo.fees.fare;

public enum TransactionFare {
    PREMIUM("PRIORITY_SERVICE"),
    NOMINAL("WITHDRAWL_SERVICE"),
    DISCOUNTED("DEPOSIT_SERVICE"),
    BASIC("INTRADAY_SERVICE");

    private String fare;

    TransactionFare(String fare){
        this.fare = fare;
    }

    public String getFare() {
        return fare;
    }

}
