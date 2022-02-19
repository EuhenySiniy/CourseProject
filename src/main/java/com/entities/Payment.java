package com.entities;

public class Payment {
    private long paymentId;
    private long sampleId;
    private long cardNum;
    private double sum;
    private String status;

    public Payment(long paymentId, long sampleId, long cardNum, double sum, String status) {
        this.paymentId = paymentId;
        this.sampleId = sampleId;
        this.cardNum = cardNum;
        this.sum = sum;
        this.status = status;
    }

    public Payment(long sampleId, long cardNum, double sum) {
        this.sampleId = sampleId;
        this.cardNum = cardNum;
        this.sum = sum;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public long getSampleId() {
        return sampleId;
    }

    public void setSampleId(long sampleId) {
        this.sampleId = sampleId;
    }

    public long getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", sampleId=" + sampleId +
                ", cardNum=" + cardNum +
                ", sum=" + sum +
                ", status='" + status + '\'' +
                '}';
    }
}
