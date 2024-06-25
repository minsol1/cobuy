package com.cobuy.BE.auth.dao;

import com.cobuy.BE.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao{

    boolean existsByUserId(String userId);

    UserDto findByUserId(String userId);

    void save(UserDto userDto);
}
