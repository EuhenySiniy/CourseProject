package com.DAO;

import com.entities.UserAddress;

import java.util.List;

public interface AddressDao {
    boolean createNewAddress(List<UserAddress> addressInfo);

    UserAddress getUserAddress(String email);

    boolean updateAddress(UserAddress address);

    boolean deleteAddress(String email);
}
