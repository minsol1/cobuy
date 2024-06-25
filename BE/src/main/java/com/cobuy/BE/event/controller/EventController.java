package com.cobuy.BE.event.controller;


import com.cobuy.BE.common.model.FileInfoDto;
import com.cobuy.BE.event.model.EventDto;
import com.cobuy.BE.event.model.EventListDto;
import com.cobuy.BE.event.model.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/event")
@Tag(name = "이벤트 컨트롤러", description = "이벤트를 등록, 수정, 삭제, 목록, 상세보기 등 전반적인 처리를 하는 클래스.")
@Slf4j
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        super();
        this.eventService = eventService;
    }

    @Value("${file.path}")
    private String uploadPath;

    @Value("${file.path.upload-images}")
    private String uploadImagePath;

    @Value("${file.path.upload-files}")
    private String uploadFilePath;

    @Operation(summary = "이벤트 작성", description = "새로운 이벤트 정보를 입력한다.")
    @PostMapping("/writeArticle")
    public ResponseEntity<?> writeArticle(
            @RequestBody @Parameter(description = "작성 이벤트 정보.", required = true) EventDto eventDto,
            @RequestParam(value = "fileInfos", required = false) MultipartFile[] files) {

        try {
            // FileUpload 관련 설정.
            if (files != null && files.length > 0) {
                List<FileInfoDto> fileInfoList = new ArrayList<>();
                for (MultipartFile file : files) {
                    FileInfoDto fileInfoDto = new FileInfoDto();
                    String originalFileName = file.getOriginalFilename();
                    if (originalFileName != null && !originalFileName.isEmpty()) {
                        String today = new SimpleDateFormat("yyMMdd").format(new Date());
                        String saveFolder = uploadPath + File.separator + today;
                        File folder = new File(saveFolder);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }
                        String saveFileName = UUID.randomUUID().toString()
                                + originalFileName.substring(originalFileName.lastIndexOf('.'));
                        file.transferTo(new File(folder, saveFileName));

                        fileInfoDto.setOriginalFile(originalFileName);
                        fileInfoDto.setSaveFolder(today);
                        fileInfoDto.setSaveFile(saveFileName);
                    }
                    fileInfoList.add(fileInfoDto);
                }
                eventDto.setFileInfos(fileInfoList);
            }

            eventService.writeArticle(eventDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "이벤트 목록", description = "모든 이벤트의 정보를 반환한다.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "이벤트 목록 OK!!"),
            @ApiResponse(responseCode = "404", description = "페이지 없음!!"),
            @ApiResponse(responseCode = "500", description = "서버 에러!!") })
    @GetMapping
    public ResponseEntity<?> listArticle(
            @RequestParam @Parameter(description = "이벤트를 얻기 위한 부가정보.", required = true) Map<String, String> map) {
        log.info("listArticle map - {}", map);

        try {
            EventListDto eventListDto = eventService.listArticle(map);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(eventListDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "이벤트 보기", description = "이벤트 번호에 해당하는 이벤트의 정보를 반환한다.")
    @GetMapping("/{eventNo}")
    public ResponseEntity<EventDto> getArticle(
            @PathVariable("eventNo") @Parameter(name = "eventNo", description = "얻어올 이벤트의 번호.", required = true) int eventNo)
            throws Exception {
        log.info("getArticle - 호출 : " + eventNo);
        eventService.updateHit(eventNo);
        return new ResponseEntity<EventDto>(eventService.getArticle(eventNo), HttpStatus.OK);
    }

    @Operation(summary = "수정할 이벤트 얻기", description = "이벤트 번호에 해당하는 이벤트의 정보를 반환한다.")
    @GetMapping("/modify/{eventNo}")
    public ResponseEntity<EventDto> getModifyArticle(
            @PathVariable("eventNo") @Parameter(name = "eventNo", description = "얻어올 이벤트의 번호.", required = true) int eventNo)
            throws Exception {
        log.info("getModifyArticle - 호출 : " + eventNo);
        return new ResponseEntity<EventDto>(eventService.getArticle(eventNo), HttpStatus.OK);
    }

    @Operation(summary = "이벤트 수정", description = "수정할 이벤트 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    @PutMapping("/updateArticle")
    public ResponseEntity<?> modifyArticle(
            @RequestBody @Parameter(description = "수정할 이벤트 정보.", required = true) EventDto eventDto,
            @RequestParam(value = "fileInfos", required = false) MultipartFile[] files) throws Exception {

        try {
            // FileUpload 관련 설정.
            if (files != null && files.length > 0) {
                List<FileInfoDto> fileInfoList = new ArrayList<>();
                for (MultipartFile file : files) {
                    FileInfoDto fileInfoDto = new FileInfoDto();
                    String originalFileName = file.getOriginalFilename();
                    if (originalFileName != null && !originalFileName.isEmpty()) {
                        String today = new SimpleDateFormat("yyMMdd").format(new Date());
                        String saveFolder = uploadPath + File.separator + today;
                        File folder = new File(saveFolder);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }
                        String saveFileName = UUID.randomUUID().toString()
                                + originalFileName.substring(originalFileName.lastIndexOf('.'));
                        file.transferTo(new File(folder, saveFileName));

                        fileInfoDto.setOriginalFile(originalFileName);
                        fileInfoDto.setSaveFolder(today);
                        fileInfoDto.setSaveFile(saveFileName);
                    }
                    fileInfoList.add(fileInfoDto);
                }
                eventDto.setFileInfos(fileInfoList);
            }

            eventService.modifyArticle(eventDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "이벤트 삭제", description = "이벤트 번호에 해당하는 이벤트의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    @DeleteMapping("/{eventNo}")
    public ResponseEntity<String> deleteArticle(
            @PathVariable("eventNo") @Parameter(name = "eventNo", description = "삭제할 이벤트의 번호.", required = true) int eventNo)
            throws Exception {
        log.info("deleteArticle - 호출");
        eventService.deleteArticle(eventNo);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
