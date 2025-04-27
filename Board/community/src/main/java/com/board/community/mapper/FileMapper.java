package com.board.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.community.domain.Files;

@Mapper
public interface FileMapper {
    
    public List<Files> list() throws Exception;

    public Files select(String id) throws Exception;

    public int insert(Files file) throws Exception;

    public int update(Files file) throws Exception;

    public int delete(String id) throws Exception;

}
