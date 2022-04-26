package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.UserDashInfo;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String family_id, String firstName,
                   String lastName, String email, String username, String password, String role);

    List<User> getUsersByFamilyId(String familyId);

    List<UserDashInfo> getUserDashboardInfoByFamilyId(String familyId);
}
