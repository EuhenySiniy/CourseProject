package com.DAO;

import com.entities.User;
import com.entities.UserAddress;

import java.util.List;
import java.util.ListIterator;

public interface AddressDao {
    boolean createNewAddress(List<UserAddress> addressInfo);

    UserAddress getUserAddress(String email, String street, int apartNum);

    List<UserAddress> getAllUserAddresses(String email);

    boolean updateAddress(UserAddress address);

    boolean deleteAddress(String email, String street, int apartNum);
}
