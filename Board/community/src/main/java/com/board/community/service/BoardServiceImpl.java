package com.board.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.community.domain.Board;
import com.board.community.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
    
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public java.util.List<Board> list() throws Exception {

        List<Board> list = boardMapper.list();
        return list;

    }

    @Override
    public Board select(String id) throws Exception {

        Board board = boardMapper.select(id);
        return board;

    }

    @Override
    public int insert(Board board) throws Exception {

        int result = boardMapper.insert(board);

        List<MultipartFile> fileList = board.getFileList();

        if( fileList != null )
            for (MultipartFile file : fileList) {

                log.info("----------------------------------");
                log.info("원본 파일명 : " + file.getOriginalFilename());
                log.info("컨텐츠파일 : " + file.getContentType());
                log.info("파라미터명 : " + file.getName());
                log.info("용량 : " + file.getSize() + " Bytes");
                log.info("----------------------------------");

            }



        return result;

    }

    @Override
    public int update(Board board) throws Exception {

        int result = boardMapper.update(board);
        return result;

    }

    @Override
    public int delete(String id) throws Exception {

        int result = boardMapper.delete(id);
        return result;

    }
    
    

}
