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
        
        // 답글 등록
        int result = commentMapper.insert(comment);
        
        // 댓글 등록
        // : 댓글 번호를 부모 댓글 번호로 수정 (no = parent_no)
        int parentNo = comment.getParentNo();
        if( parentNo == 0 ) {
            comment.setParentNo(comment.getNo());
            commentMapper.update(comment);
        }

        return result;
    }

    @Override
    public int update(Comments comment) throws Exception {
        String id = comment.getId();
        String updatedWriter = comment.getWriter();     // 수정된 작성자
        String updatedContent = comment.getContent();   // 수정된 내용
        comment = commentMapper.select(id);             // parentNo 유지

        comment.setWriter(updatedWriter);
        comment.setContent(updatedContent);
        int result = commentMapper.update(comment);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = 0;
        Comments deleteComment = commentMapper.select(id);

        // 답글 삭제
        int parentNo = deleteComment.getNo();
        int no = deleteComment.getParentNo();
        if(no == parentNo) {
            result = commentMapper.deleteReplyByParent(parentNo);
        }
        else {
            // 댓글 삭제
            result = commentMapper.delete(id);
        }

        // 댓글 삭제
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

    @Override
    public List<Comments> replyList(int parentNo) throws Exception {
        List<Comments> replyList = commentMapper.replyList(parentNo);
        return replyList;
    }

    @Override
    public int deleteReplyByParent(int parentNo) throws Exception {
        int result = commentMapper.deleteReplyByParent(parentNo);
        return result;
    }
    
}
