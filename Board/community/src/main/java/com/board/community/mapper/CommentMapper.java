package com.board.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.community.domain.Comments;

@Mapper
public interface CommentMapper {

    public List<Comments> list() throws Exception;

    public Comments select(String id) throws Exception;

    public int insert(Comments comment) throws Exception;

    public int update(Comments comment) throws Exception;

    public int delete(String id) throws Exception;

    // 부모 기준 댓글 목록
    public List<Comments> listByParent(int boardNo) throws Exception;
    
    // 부모 기준 댓글 삭제
    public int deleteByParent(int boardNo) throws Exception;

    // 답글 목록
    public List<Comments> replyList(int parentNo) throws Exception;

    // 부모 댓글 기준 답글 삭제
    public int deleteReplyByParent(int parentNo) throws Exception;

}
