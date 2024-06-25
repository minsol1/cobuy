package com.cobuy.BE.event.model;

import com.cobuy.BE.common.model.FileInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(title = "EventDto : 이벤트정보", description = "이벤트글의 상세 정보를 나타낸다.")
public class EventDto {
    @Schema(description = "이벤트번호")
    private int articleNo;

    @Schema(description = "글제목")
    private String subject;

    @Schema(description = "글내용")
    private String content;

    @Schema(description = "조회수")
    private int hit;

    @Schema(description = "작성일")
    private String registerTime;

    @Schema(description = "작성자 아이디")
    private String userId;

    @Schema(description = "작성자 별명")
    private String nickName;

    @Schema(description = "현재 인원")
    private int currentPeople;

    @Schema(description = "전체 인원")
    private int totalPeople;

    @Schema(description = "가격")
    private int price;

    @Schema(description = "거래지역")
    private String location;

    @Schema(description = "거래날짜")
    private String transactionDate;

    @Schema(description = "업로드 파일정보")
    private List<FileInfoDto> fileInfos;
}
