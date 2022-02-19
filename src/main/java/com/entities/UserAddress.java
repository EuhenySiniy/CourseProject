package com.entities;

public class UserAddress {
    private long id;
    private String city;
    private String street;
    private String houseNum;
    private int apartmentNum;
    private String userLogin;
    private long userId;

    public UserAddress(long id, String city, String street, String houseNum, int apartmentNum, long userId) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.apartmentNum = apartmentNum;
        this.userId = userId;
    }

    public UserAddress (String city, String street, String houseNum, int apartmentNum, String userLogin) {
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.apartmentNum = apartmentNum;
        this.userLogin = userLogin;
    }

    public UserAddress (long id, String city, String street, String houseNum, int apartmentNum) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.apartmentNum = apartmentNum;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public int getApartmentNum() {
        return apartmentNum;
    }

    public String getUserLogin() {
        return userLogin;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNum='" + houseNum + '\'' +
                ", apartmentNum=" + apartmentNum +
                ", userId=" + userId +
                '}';
    }
}
