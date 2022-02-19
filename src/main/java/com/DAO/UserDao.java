package com.DAO;

import com.entities.Payment;
import com.entities.User;

import java.util.List;

public interface UserDao {
    boolean createNewUsers(List<User> userList);

    User getUserByLogin(String email);

    boolean deleteUserByLogin(String email);

    boolean getAllUsers();
}
