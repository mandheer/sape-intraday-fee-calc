package com.sapient.demo.fees.data;

public class TransactionDetails {
    private String externalTransactionId;
    private String clientId;
    private String securityId;
    private TransactionType type;
    private String transDate;
    private double currentMktVal;
    private TransactionPriority priority;

    public String getExternalTransactionId() {
        return externalTransactionId;
    }

    public void setExternalTransactionId(String externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
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

    public double getCurrentMktVal() {
        return currentMktVal;
    }

    public void setCurrentMktVal(double currentMktVal) {
        this.currentMktVal = currentMktVal;
    }

    public TransactionPriority getPriority() {
        return priority;
    }

    public void setPriority(TransactionPriority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "TransactionDetails{" +
                "externalTransactionId='" + externalTransactionId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", securityId='" + securityId + '\'' +
                ", type=" + type +
                ", transDate='" + transDate + '\'' +
                ", currentMktVal=" + currentMktVal +
                ", priority=" + priority +
                '}';
    }
}
