package com.cobuy.BE.user.model.mapper;

import com.cobuy.BE.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    boolean existsByUserId(String userId);

    UserDto findByUserId(String userId);

    void save(UserDto userDto);
}
