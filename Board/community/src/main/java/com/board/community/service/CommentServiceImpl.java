package com.board.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.community.domain.Comments;
import com.board.community.mapper.CommentMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comments> list() throws Exception {
        List<Comments> commentList = commentMapper.list();
        return commentList;
    }

    @Override
    public Comments select(String id) throws Exception {
        Comments comment = commentMapper.select(id);
        return comment;
    }

    @Override
    public int insert(Comments comment) throws Exception {
        int result = commentMapper.insert(comment);
        return result;
    }

    @Override
    public int update(Comments comment) throws Exception {
        int result = commentMapper.update(comment);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = commentMapper.delete(id);
        return result;
    }

    @Override
    public List<Comments> listByParent(int boardNo) throws Exception {
        List<Comments> commentList = commentMapper.listByParent(boardNo);
        return commentList;
    }

    @Override
    public int deleteByParent(int boardNo) throws Exception {
        int result = commentMapper.deleteByParent(boardNo);
        return result;
    }
    
}
