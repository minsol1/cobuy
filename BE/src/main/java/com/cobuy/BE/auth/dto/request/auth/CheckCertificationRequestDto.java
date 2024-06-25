package com.cobuy.BE.auth.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckCertificationRequestDto {

    @Schema(description = "유저아이디" , example = "testId")
    @NotBlank
    private String id;

    @Schema(description = "유저이메일" , example = "testEmail@cobuy.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "인증코드" , example = "0428")
    @NotBlank
    private String certificationNumber;
}
