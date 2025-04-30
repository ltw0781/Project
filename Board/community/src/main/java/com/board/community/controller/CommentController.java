package com.board.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.community.domain.Comments;
import com.board.community.service.CommentService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    /**
     * 댓글 등록
     * @param comment
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("")
    public String commentPost(@RequestBody Comments comment) throws Exception{

        int result = commentService.insert(comment);
        if( result > 0 )
            return "SUCCESS";
        
        return "FAIL";
    }

    /**
     * 댓글 등록
     * @param model
     * @param boardNo
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public String commentList(Model model, @RequestParam("boardNo") int boardNo) throws Exception{
        List<Comments> commentList = commentService.listByParent(boardNo);
        model.addAttribute("commentList", commentList);
        return "/comment/list";
    }
    

}
