package com.cobuy.BE.auth.dao;

import com.cobuy.BE.auth.dto.CertificationDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface CertificationDao {
    CertificationDto findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);

    void save(CertificationDto certificationDto);
}
