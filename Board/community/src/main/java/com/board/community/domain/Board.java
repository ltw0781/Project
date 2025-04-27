package com.board.community.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Data
// @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    private int no;
    private String id;
    private String title;
    private String writer;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    // 파일 업로드 정보
    private List<MultipartFile> fileList;           // 파일 목록
    
    public Board() {

        this.id = UUID.randomUUID().toString();

    }

}
