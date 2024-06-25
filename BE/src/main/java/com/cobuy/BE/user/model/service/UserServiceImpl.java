package com.cobuy.BE.user.model.service;

import com.cobuy.BE.user.model.mapper.UserMapper;
import com.cobuy.BE.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public boolean existsByUserId(String userId) {
        return userMapper.existsByUserId(userId);
    }

    @Override
    public UserDto findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public void save(UserDto userDto) {
        userMapper.save(userDto);
    }
}
