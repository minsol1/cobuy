package com.cobuy.BE.user.model;

import com.cobuy.BE.auth.dto.request.auth.SignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;
    private String password;
    private String email;
    private String type;
    private String role;
    private String nickName;
    private String profileImage;

    public UserDto(SignUpRequestDto dto) {
        this.userId = dto.getId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.type = "app";
        this.role = "ROLE_USER";
        this.nickName = "NICKNAME";
        this.profileImage = "";
    }

    public UserDto(String userId, String email, String type){
        this.userId = userId;
        this.password = "Passw0rd";
        this.email = email;
        this.type = type;
        this.role = "ROLE_USER";
    }
}
