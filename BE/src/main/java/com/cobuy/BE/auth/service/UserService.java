package com.cobuy.BE.auth.service;

import com.cobuy.BE.user.model.UserDto;

public interface UserService {

    boolean existsByUserId(String userId);

    UserDto findByUserId(String userId);

    void save(UserDto userDto);
}
