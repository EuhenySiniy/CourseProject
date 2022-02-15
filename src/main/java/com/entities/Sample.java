package com.entities;

public class Sample {
    private int sampleId;
    private String sampleName;
    private String iban;
    private String okpo;
    private String appointment;
    private String userLogin;
    private String street;
    private int apartNum;

    public Sample() {}

    public Sample(int sampleId, String sampleName, String iban, String okpo, String appointment) {
        this.sampleId = sampleId;
        this.sampleName = sampleName;
        this.iban = iban;
        this.okpo = okpo;
        this.appointment = appointment;
    }

    public Sample(String sampleName, String iban, String okpo, String appointment, String userLogin, String street, int apartNum) {
        this.sampleName = sampleName;
        this.iban = iban;
        this.okpo = okpo;
        this.appointment = appointment;
        this.userLogin = userLogin;
        this.street = street;
        this.apartNum = apartNum;
    }

    public int getSampleId() {
        return sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getApartNum() {
        return apartNum;
    }

    public void setApartNum(int apartNum) {
        this.apartNum = apartNum;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "sampleName='" + sampleName + '\'' +
                ", iban='" + iban + '\'' +
                ", okpo='" + okpo + '\'' +
                ", appointment='" + appointment + '\'' +
                '}';
    }
}
