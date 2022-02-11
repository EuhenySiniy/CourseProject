package com.DAO;

import com.entities.User;

import java.util.List;

public interface UserDao {
    boolean createNewUsers(List<User> userList);

    User getUserFromDB(String email);

    boolean updateUser(User user);

    boolean deleteUser(String email);
}
