package com.board.community.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.community.domain.Files;
import com.board.community.service.FileService;
import com.board.community.util.MediaUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 이미지 썸네일
     * @param id         (파일 id)
     * @return
     * @throws Exception
     */
    @GetMapping("/img")
    public ResponseEntity<byte[]> thumbnail(@RequestParam("id") String id) throws Exception{
        Files file = fileService.select(id);

        String filePath = file.getFilePath();

        // 파일 객체 생성
        File  f = new File(filePath);

        // 파일 데이터
        byte[] fileData = FileCopyUtils.copyToByteArray(f);

        // 컨텐츠 타입 지정
        // 확장자로 컨텐츠 타입 지정
        // - 확장자 : .jpg, .png ...
        String ext = filePath.substring( filePath.lastIndexOf(".") + 1); // 확장자
        MediaType mediaType = MediaUtil.getMediaType(ext);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);


        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }
    
    /**
     * 다운로드드
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) throws Exception{
        Files file = fileService.select(id);

        String filePath = file.getFilePath();
        String fileName = file.getFileName();

        // 파일 객체 생성
        File f = new File(filePath);

        // 파일 데이터 생성
        byte[] fileData = FileCopyUtils.copyToByteArray(f);
        // 파일 응답을 위한 헤더 설정
        // - ContentType                : application/octect-stream
        // - Content-Disposition        : attachment; filename="파일명.확장자"
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);



        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }
    
    /**
     * 파일 삭제
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @DeleteMapping("/file/{id}")
    public String deleteFile(@PathVariable("id") String id) throws Exception{
        int result = fileService.delete(id);

        // 파일 삭제 성공
        if( result > 0 ) {
            return "SUCCESS";
        }

        // 파일 삭제 실패
        return "FAIL";
    }

    /**
     * 파일 목록
     * @param file - parentTable, parentNo
     * @return 뷰(html)
     * @throws Exception
     */
    @GetMapping("/file")
    public String fileList(Model model, Files file) throws Exception{
        List<Files> fileList = fileService.listByParent(file);
        model.addAttribute("fileList", fileList);
        return "/file/list";
    }
    

    
}
