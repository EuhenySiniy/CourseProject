package com.entities;

public class UserAddress {
    private int addressId;
    private String address;
    private String userLogin;

    public UserAddress (int id, String address, String userLogin) {
        this.addressId = id;
        this.address = address;
        this.userLogin = userLogin;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }
}
