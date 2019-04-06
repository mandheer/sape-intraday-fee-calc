package com.sapient.demo.fees.data;

public enum TransactionType {
    BUY("Buy"),
    SELL("Sell"),
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw");

    private String transationType;

    TransactionType(String transationType) {
        this.transationType = transationType;
    }

    public String getType(){
        return this.transationType;
    }

}
