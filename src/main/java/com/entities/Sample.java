package com.entities;

public class Sample {
    private long sampleId;
    private String sampleName;
    private String iban;
    private String okpo;
    private String appointment;
    private String userLogin;
    private String street;
    private int apartNum;

    public Sample(long sampleId, String sampleName, String iban, String okpo, String appointment) {
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

    public long getSampleId() {
        return sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public String getIban() {
        return iban;
    }

    public String getOkpo() {
        return okpo;
    }

    public String getAppointment() {
        return appointment;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getStreet() {
        return street;
    }

    public int getApartNum() {
        return apartNum;
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
