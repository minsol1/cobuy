package com.cobuy.BE.auth.dto.response.auth;

import com.cobuy.BE.auth.dto.response.ResponseDto;
import com.cobuy.BE.common.ResponseCode;
import com.cobuy.BE.common.ResponseMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class CheckCertificationResponseDto extends ResponseDto {

    @Schema(description = "에러 코드", example = "CF")
    private String code;

    @Schema(description = "에러 메시지", example = "CERTIFICATION_FAIL")
    private String message;

    private CheckCertificationResponseDto(){
        super();
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
    }

    public static ResponseEntity<CheckCertificationResponseDto> success(){
        CheckCertificationResponseDto responseBody = new CheckCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> certificationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

}
