package com.cobuy.BE.auth.service.implement;

import com.cobuy.BE.auth.dao.UserDao;
import com.cobuy.BE.user.model.UserDto;
import com.cobuy.BE.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public boolean existsByUserId(String userId) {
        return userDao.existsByUserId(userId);
    }

    @Override
    public UserDto findByUserId(String userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public void save(UserDto userDto) {
        userDao.save(userDto);
    }
}
