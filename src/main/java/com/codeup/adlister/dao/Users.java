package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

public interface Users {
    User findByUsername(String username);

    User findById(Long id);

    Long insert(User user);

    void changeUsername(Long id, String changeUsernameTo);

    void changeEmail(Long id, String changeEmailTo);

    void changePassword(Long id, String changePasswordTo);
}
