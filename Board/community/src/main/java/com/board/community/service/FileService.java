package com.board.community.service;

import java.util.List;

import com.board.community.domain.Files;

public interface FileService {
    
    public List<Files> list() throws Exception;

    public Files select(String id) throws Exception;

    public int insert(Files file) throws Exception;

    public int update(Files file) throws Exception;

    public int delete(String id) throws Exception;

}
