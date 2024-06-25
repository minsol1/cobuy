package com.cobuy.BE.auth.dto.response.auth;

import com.cobuy.BE.auth.dto.response.ResponseDto;
import com.cobuy.BE.common.ResponseCode;
import com.cobuy.BE.common.ResponseMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class IdCheckResponseDto extends ResponseDto {

    @Schema(description = "에러 코드", example = "DU")
    private String code;

    @Schema(description = "에러 메시지", example = "DUPLICATE ID")
    private String message;

    private IdCheckResponseDto(){
        super();
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
        // 부모 생성자 호출 -> code , meessage : ResponseCode.SUCCESS
    }

    public static ResponseEntity<IdCheckResponseDto> success() {
        IdCheckResponseDto responseBody = new IdCheckResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
