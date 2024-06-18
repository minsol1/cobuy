package com.cobuy.BE.board.controller;
/*
package com.example.board.controller;

import java.nio.charset.Charset;
import java.util.Map;

import com.example.board.model.BoardDto;
import com.example.board.model.BoardListDto;
import com.example.board.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/board")
@Tag(name = "게시판 컨트롤러", description = "게시판에 글을 등록, 수정, 삭제, 목록, 상세보기등 전반적인 처리를 하는 클래스.")
@Slf4j
public class BoardController {

    private BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService) {
        super();
        this.boardService = boardService;
    }

    @Operation(summary = "게시판 글작성", description = "새로운 게시글 정보를 입력한다.")
    @PostMapping
    public ResponseEntity<?> writeArticle(
            @RequestBody @Parameter(description = "작성글 정보.", required = true) BoardDto boardDto) {
        log.info("writeArticle boardDto - {}", boardDto);
        try {
            boardService.writeArticle(boardDto);
//			return ResponseEntity.ok().build();
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "게시판 글목록", description = "모든 게시글의 정보를 반환한다.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "게시글목록 OK!!"),
            @ApiResponse(responseCode = "404", description = "페이지없어!!"),
            @ApiResponse(responseCode = "500", description = "서버에러!!") })
    @GetMapping
    public ResponseEntity<?> listArticle(
            @RequestParam @Parameter(description = "게시글을 얻기위한 부가정보.", required = true) Map<String, String> map) {
        log.info("listArticle map - {}", map);
        try {
            BoardListDto boardListDto = boardService.listArticle(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(boardListDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "게시판 글보기", description = "글번호에 해당하는 게시글의 정보를 반환한다.")
    @GetMapping("/{articleno}")
    public ResponseEntity<BoardDto> getArticle(
            @PathVariable("articleno") @Parameter(name = "articleno", description = "얻어올 글의 글번호.", required = true) int articleno)
            throws Exception {
        log.info("getArticle - 호출 : " + articleno);
        boardService.updateHit(articleno);
        return new ResponseEntity<BoardDto>(boardService.getArticle(articleno), HttpStatus.OK);
    }

    @Operation(summary = "수정 할 글 얻기", description = "글번호에 해당하는 게시글의 정보를 반환한다.")
    @GetMapping("/modify/{articleno}")
    public ResponseEntity<BoardDto> getModifyArticle(
            @PathVariable("articleno") @Parameter(name = "articleno", description = "얻어올 글의 글번호.", required = true) int articleno)
            throws Exception {
        log.info("getModifyArticle - 호출 : " + articleno);
        return new ResponseEntity<BoardDto>(boardService.getArticle(articleno), HttpStatus.OK);
    }

    @Operation(summary = "게시판 글수정", description = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    @PutMapping
    public ResponseEntity<String> modifyArticle(
            @RequestBody @Parameter(description = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
        log.info("modifyArticle - 호출 {}", boardDto);

        boardService.modifyArticle(boardDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게시판 글삭제", description = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    @DeleteMapping("/{articleno}")
    public ResponseEntity<String> deleteArticle(@PathVariable("articleno") @Parameter(name = "articleno", description = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
        log.info("deleteArticle - 호출");
        boardService.deleteArticle(articleno);
        return ResponseEntity.ok().build();

    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
*/


import com.cobuy.BE.board.model.BoardDto;
import com.cobuy.BE.board.model.BoardListDto;
import com.cobuy.BE.board.model.FileInfoDto;
import com.cobuy.BE.board.model.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/board")
@Tag(name = "게시판 컨트롤러", description = "게시판에 글을 등록, 수정, 삭제, 목록, 상세보기 등 전반적인 처리를 하는 클래스.")
@Slf4j
public class BoardController {

    private final BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService) {
        super();
        this.boardService = boardService;
    }

    @Value("${file.path}")
    private String uploadPath;

    @Value("${file.path.upload-images}")
    private String uploadImagePath;

    @Value("${file.path.upload-files}")
    private String uploadFilePath;

    @Operation(summary = "게시판 글작성", description = "새로운 게시글 정보를 입력한다.")
    @PostMapping("/writeArticle")
    public ResponseEntity<?> writeArticle(
            @RequestBody @Parameter(description = "작성글 정보.", required = true) BoardDto boardDto,
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
                boardDto.setFileInfos(fileInfoList);
            }

            boardService.writeArticle(boardDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "게시판 글목록", description = "모든 게시글의 정보를 반환한다.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "게시글 목록 OK!!"),
            @ApiResponse(responseCode = "404", description = "페이지 없음!!"),
            @ApiResponse(responseCode = "500", description = "서버 에러!!") })
    @GetMapping
    public ResponseEntity<?> listArticle(
            @RequestParam @Parameter(description = "게시글을 얻기 위한 부가정보.", required = true) Map<String, String> map) {
        log.info("listArticle map - {}", map);

        try {
            BoardListDto boardListDto = boardService.listArticle(map);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(boardListDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "게시판 글보기", description = "글번호에 해당하는 게시글의 정보를 반환한다.")
    @GetMapping("/{articleno}")
    public ResponseEntity<BoardDto> getArticle(
            @PathVariable("articleno") @Parameter(name = "articleno", description = "얻어올 글의 글번호.", required = true) int articleno)
            throws Exception {
        log.info("getArticle - 호출 : " + articleno);
        boardService.updateHit(articleno);
        return new ResponseEntity<BoardDto>(boardService.getArticle(articleno), HttpStatus.OK);
    }

    @Operation(summary = "수정 할 글 얻기", description = "글번호에 해당하는 게시글의 정보를 반환한다.")
    @GetMapping("/modify/{articleno}")
    public ResponseEntity<BoardDto> getModifyArticle(
            @PathVariable("articleno") @Parameter(name = "articleno", description = "얻어올 글의 글번호.", required = true) int articleno)
            throws Exception {
        log.info("getModifyArticle - 호출 : " + articleno);
        return new ResponseEntity<BoardDto>(boardService.getArticle(articleno), HttpStatus.OK);
    }

    @Operation(summary = "게시판 글수정", description = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    @PutMapping("/updateArticle")
    public ResponseEntity<?> modifyArticle(
            @RequestBody @Parameter(description = "수정할 글 정보.", required = true) BoardDto boardDto,
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
                boardDto.setFileInfos(fileInfoList);
            }

            boardService.modifyArticle(boardDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "게시판 글삭제", description = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    @DeleteMapping("/{articleno}")
    public ResponseEntity<String> deleteArticle(
            @PathVariable("articleno") @Parameter(name = "articleno", description = "삭제할 글의 글번호.", required = true) int articleno)
            throws Exception {
        log.info("deleteArticle - 호출");
        boardService.deleteArticle(articleno);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

