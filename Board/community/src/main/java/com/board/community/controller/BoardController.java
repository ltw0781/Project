package com.board.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.community.domain.Board;
import com.board.community.domain.Files;
import com.board.community.service.BoardService;
import com.board.community.service.FileService;

import lombok.extern.slf4j.Slf4j;




/**
 *  게시글 목록             /board/list     [GET]
 *  게시글 조회             /board/select   [GET]
 *  게시글 등록             /board/insert   [GET]
 *  게시글 등록 처리        /board/insert   [POST]
 *  게시글 수정             /board/update   [GET]
 *  게시글 수정 처리        /board/update   [POST]
 *  게시글 삭제 처리        /board/delete   [POST]
 */
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileService fileService;
    
    /**
     * 게시글 목록록
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public String list(Model model) throws Exception{

        List<Board> boardList = boardService.list();
        model.addAttribute("boardList", boardList);
        return "/board/list";

    }

    /**
     * 게시글 조회
     * @param id
     * @return
     */
    @GetMapping("/select")
    public String select(Model model, @RequestParam("id") String id, Files file) throws Exception{

        // 게시글 조회
        Board board = boardService.select(id);
        model.addAttribute("board", board);

        // 파일 목록 조회
        file.setParentNo(board.getNo());
        file.setParentTable("board");

        log.info("file : " + file);
        List<Files> fileList = fileService.listByParent(file);
        model.addAttribute("fileList", fileList);

        return "/board/select";

    }
    

    /**
     * 게시글 등록
     * @return
     */
    @GetMapping("/insert")
    public String insert() {

        return "/board/insert";
    }
    

    /**
     * 게시글 등록 처리
     * @param board
     * @return
     */
    @PostMapping("/insert")
    public String insertPost(Board board) throws Exception{
        
        log.info("board : " + board);

        int result = boardService.insert(board);

        if( result > 0 ){
            return "redirect:/board/list";
        }
        return "redirect:/board/insert?error";
    }
    

    /**
     * 게시글 수정
     * @param id
     * @return
     */
    @GetMapping("/update")
    public String update(Model model, @RequestParam("id") String id, Files file) throws Exception{

        Board board = boardService.select(id);
        model.addAttribute("board", board);

        // 파일 목록 조회
        file.setParentNo(board.getNo());
        file.setParentTable("board");

        log.info("file : " + file);
        List<Files> fileList = fileService.listByParent(file);
        model.addAttribute("fileList", fileList);
        
        return "/board/update";

    }
    

    /**
     * 게시글 수정 처리
     * @param board
     * @return
     */
    @PostMapping("/update")
    public String updatePost(Board board) throws Exception{
        
        int result = boardService.update(board);

        if( result > 0 ) {
            return "redirect:/board/list";
        }

        return "redirect:/board/update?error&id="+board.getId();

    }
    

    /**
     * 게시글 삭제 처리
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id ) throws Exception{

        int result = boardService.delete(id);

        if( result > 0 ) {
            return "redirect:/board/list";
        }
        return "redirect:/board/update?error&id="+id;

    }
    

}
