package com.cobuy.BE.auth.controller;

import com.cobuy.BE.auth.dto.request.auth.*;
import com.cobuy.BE.auth.dto.response.ResponseDto;
import com.cobuy.BE.auth.dto.response.auth.*;
import com.cobuy.BE.auth.service.implement.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@Tag(name = "인증 컨트롤러", description = "로그인, 회원가입 등 인증 관련 처리 클래스")
public class AuthController {

    private final AuthServiceImpl authService;

    @Operation(summary = "아이디 유효성 검증", description = "사용 가능한 아이디인지 검증")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "사용 가능한 아이디",  content = {@Content(schema = @Schema(implementation = ResponseDto.class))}),
                    @ApiResponse(responseCode = "400", description = "이미 존재하는 아이디입니다.",  content = {@Content(schema = @Schema(implementation = IdCheckResponseDto.class))}),
                    @ApiResponse(
                            responseCode = "500",
                            description = "내부 서버 에러",
                            content = {@Content(schema = @Schema(implementation = IdCheckResponseDto.class),
                            examples = @ExampleObject(value = "{\"code\":\"DBE\",\"message\":\"DATABASE_ERR\"}"))}
                    ),
            })
    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck(@RequestBody IdCheckRequsetDto requestBody) {
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @Operation(summary = "인증 코드 발송", description = "ID & 이메일 유효성 검증 및 인증코드 발송")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "인증코드 발송 성공",  content = {@Content(schema = @Schema(implementation = ResponseDto.class))}),
                    @ApiResponse(responseCode = "400", description = "이미 존재하는 아이디입니다.",  content = {@Content(schema = @Schema(implementation = IdCheckResponseDto.class))}),
                    @ApiResponse(
                            responseCode = "500",
                            description = "내부 서버 에러",
                            content = {@Content(schema = @Schema(implementation = IdCheckResponseDto.class),
                                    examples = @ExampleObject(value = "{\"code\":\"DBE\",\"message\":\"DATABASE_ERR\"}"))}
                    ),
            })
    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(@RequestBody @Valid EmailCertificationRequestDto requestBody) {
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @Operation(summary = "인증 코드 검증", description = "인증코드 유효성 검증")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "유효한 인증코드",  content = {@Content(schema = @Schema(implementation = ResponseDto.class))}),
                    @ApiResponse(responseCode = "401", description = "유효하지 않은 사용자 or 인증코드",  content = {@Content(schema = @Schema(implementation = CheckCertificationResponseDto.class))}),
                    @ApiResponse(
                            responseCode = "500",
                            description = "내부 서버 에러",
                            content = {@Content(schema = @Schema(implementation = IdCheckResponseDto.class),
                                    examples = @ExampleObject(value = "{\"code\":\"DBE\",\"message\":\"DATABASE_ERR\"}"))}
                    ),
            })
    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Valid CheckCertificationRequestDto requestBody
    ) {
        log.info("실행");
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }


    @Operation(summary = "회원가입", description = "회원가입 검증")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "회원가입 성공",  content = {@Content(schema = @Schema(implementation = ResponseDto.class))}),
                    @ApiResponse(responseCode = "400", description = "이미 존재하는 아이디입니다.",  content = {@Content(schema = @Schema(implementation = IdCheckResponseDto.class))}),
                    @ApiResponse(responseCode = "401", description = "유효하지 않은 사용자 or 인증코드",  content = {@Content(schema = @Schema(implementation = CheckCertificationResponseDto.class))}),
                    @ApiResponse(
                            responseCode = "500",
                            description = "내부 서버 에러",
                            content = {@Content(schema = @Schema(implementation = ResponseDto.class),
                                    examples = @ExampleObject(value = "{\"code\":\"DBE\",\"message\":\"DATABASE_ERR\"}"))}
                    ),
            })
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody
    ) {

        /**
         * password validateion 패턴 지정
         * 하나 이상의 영문자와 숫자로 구성
         * 8 ~ 13자 글자수 제한
         */

        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @Operation(summary = "로그인", description = "로그인 검증")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "로그인 성공",  content = {@Content(schema = @Schema(implementation = ResponseDto.class))}),
                    @ApiResponse(responseCode = "401", description = "유효하지 않은 사용자 정보 입니다.",  content = {@Content(schema = @Schema(implementation = CheckCertificationResponseDto.class))}),
                    @ApiResponse(
                            responseCode = "500",
                            description = "내부 서버 에러",
                            content = {@Content(schema = @Schema(implementation = ResponseDto.class),
                                    examples = @ExampleObject(value = "{\"code\":\"DBE\",\"message\":\"DATABASE_ERR\"}"))}
                    ),
            })
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody
    ) {

        log.info("/sign-in - SignInRequestDto : {}", requestBody.toString());
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

}
