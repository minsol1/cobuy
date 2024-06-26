package com.cobuy.BE.auth.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IdCheckRequsetDto {

    @Schema(description = "유저아이디" , example = "testId")
    @NotBlank
    private String id;
}
