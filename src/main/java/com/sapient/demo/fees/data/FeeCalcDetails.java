package com.sapient.demo.fees.data;

public class FeeCalcDetails {
    private String clientId;
    private String securityId;
    private TransactionType type;
    private String transDate;
    private TransactionPriority priority;
    private double processingFee;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public TransactionPriority getPriority() {
        return priority;
    }

    public void setPriority(TransactionPriority priority) {
        this.priority = priority;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }
}
