package com.DAO;

import com.entities.UserAddress;

import java.util.List;

public interface AddressDao {
    boolean createNewAddress(List<UserAddress> addressInfo);

    List<UserAddress> getAllUserAddresses(String email);

    boolean deleteAddressById(long addressId);

    boolean getAllAddress();
}
