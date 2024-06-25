package com.cobuy.BE.auth.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignInRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
