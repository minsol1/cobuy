package com.cobuy.BE.auth.mapper;

import com.cobuy.BE.auth.dto.CertificationDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface CertificationMapper {
    CertificationDto findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);

    void save(CertificationDto certificationDto);
}
