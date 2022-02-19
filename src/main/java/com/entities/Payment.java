package com.entities;

import java.sql.Timestamp;

public class Payment {
    private long paymentId;
    private long sampleId;
    private long cardNum;
    private double sum;
    private String status;
    private Timestamp dateCreate;
    private Timestamp dateStatus;

    public Payment(long paymentId, long sampleId, long cardNum, double sum, String status, Timestamp dateCreate, Timestamp dateStatus) {
        this.paymentId = paymentId;
        this.sampleId = sampleId;
        this.cardNum = cardNum;
        this.sum = sum;
        this.status = status;
        this.dateCreate = dateCreate;
        this.dateStatus = dateStatus;
    }

    public Payment(long sampleId, long cardNum, double sum) {
        this.sampleId = sampleId;
        this.cardNum = cardNum;
        this.sum = sum;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public long getSampleId() {
        return sampleId;
    }

    public long getCardNum() {
        return cardNum;
    }

    public double getSum() {
        return sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", sampleId=" + sampleId +
                ", cardNum=" + cardNum +
                ", sum=" + sum +
                ", status='" + status + '\'' +
                ", dateCreate=" + dateCreate +
                ", dateStatus=" + dateStatus +
                '}';
    }
}
