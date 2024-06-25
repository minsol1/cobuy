package com.cobuy.BE.auth.service.implement;

import com.cobuy.BE.auth.dao.CertificationDao;
import com.cobuy.BE.auth.dao.UserDao;
import com.cobuy.BE.auth.dto.CertificationDto;
import com.cobuy.BE.user.model.UserDto;
import com.cobuy.BE.auth.dto.request.auth.*;
import com.cobuy.BE.auth.dto.response.ResponseDto;
import com.cobuy.BE.auth.dto.response.auth.*;
import com.cobuy.BE.auth.service.AuthService;
import com.cobuy.BE.common.CertificationNumber;
import com.cobuy.BE.provider.EmailProvider;
import com.cobuy.BE.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final CertificationDao certificationDao;

    private final JwtProvider jwtProvider;
    private final EmailProvider emailProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequsetDto dto) {

        try {
            String userId = dto.getId();
            boolean isExistId = userDao.existsByUserId(userId);

            if (isExistId)
                return IdCheckResponseDto.duplicateId();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            String userId = dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userDao.existsByUserId(userId);

            if (isExistId)
                return EmailCertificationResponseDto.duplicateId();



            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed)
                return EmailCertificationResponseDto.mailSendFail();

            certificationDao.deleteByUserId(userId);

            CertificationDto certificationDto = new CertificationDto(userId, email, certificationNumber);
            certificationDao.save(certificationDto);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try {
            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationDto certificationDto = certificationDao.findByUserId(userId);

            if (certificationDto == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationDto.getEmail().equals(email) && certificationDto.getCertificationNumber().equals(certificationNumber);

            if (!isMatched)
                return CheckCertificationResponseDto.certificationFail();


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try {

            log.info("singup실행");
            String userId = dto.getId();

            boolean isExistedId = userDao.existsByUserId(userId);
            if (isExistedId)
                return SignUpResponseDto.duplicateId();

            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();
            CertificationDto certificationDto = certificationDao.findByUserId(userId);

            boolean isMatched = certificationDto.getEmail().equals(email) &&
                    certificationDto.getCertificationNumber().equals(certificationNumber);

            if (!isMatched)
                return SignUpResponseDto.certificationFail();

            String password = dto.getPassword();
            log.info("dto.getPassword = {}", dto.getPassword());
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserDto userDto = new UserDto(dto);
            userDao.save(userDto);

            certificationDao.deleteByUserId(userId);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;

        try {
            String userId = dto.getId();
            UserDto userDto = userDao.findByUserId(userId);

            log.info("signIn.SignInRequestDto = {}", dto);
            if(userDto == null) SignInResponseDto.signInfail();


            log.info("signIn.userDto = {}", userDto);

            String password = dto.getPassword();
            String encodedPassword = userDto.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);

            if(!isMatched) return SignInResponseDto.signInfail();

            token = jwtProvider.create(userId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
}
