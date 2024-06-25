package com.cobuy.BE.event.model;

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
@Schema(title = "EventListDto : 이벤트글 목록 & 페이지 정보", description = "이벤트글 목록과 현재 페이지와 전체 페이지 정보를 나타낸다.")
public class EventListDto {
    @Schema(description = "이벤트목록")
    private List<EventDto> articles;

    @Schema(description = "현재 페이지번호")
    private int currentPage;

    @Schema(description = "전체 페이지 수")
    private int totalPageCount;
}
