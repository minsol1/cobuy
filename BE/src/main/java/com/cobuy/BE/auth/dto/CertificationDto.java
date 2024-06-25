package com.cobuy.BE.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificationDto {
    private String userId;
    private String email;
    private String certificationNumber;
}
