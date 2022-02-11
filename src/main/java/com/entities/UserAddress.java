package com.entities;

public class UserAddress {
    private int id;
    private String city;
    private String street;
    private String houseNum;
    private int apartmentNum;
    private String userLogin;

    public UserAddress (String city, String street, String houseNum, int apartmentNum, String userLogin) {
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.apartmentNum = apartmentNum;
        this.userLogin = userLogin;
    }

    public UserAddress (int id, String city, String street, String houseNum, int apartmentNum) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.apartmentNum = apartmentNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public int getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(int apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNum=" + houseNum +
                ", apartmentNum=" + apartmentNum +
                '}';
    }
}
