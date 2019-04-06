package com.sapient.demo.fees.data;

public enum TransactionPriority {
    Y("HIGH"),
    N("NORMAL");

    private String transPriority;

    TransactionPriority(String transPriority){
        this.transPriority = transPriority;
    }

    public String getTransPriority() {
        return transPriority;
    }
}
