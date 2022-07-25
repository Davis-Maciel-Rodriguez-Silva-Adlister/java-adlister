package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    Long insert(User user);

    User changeUsername(Long id, String changeUsernameTo);

    User changeEmail(Long id, String changeEmailTo);

    User changePassword(Long id, String changePasswordTo);
}
