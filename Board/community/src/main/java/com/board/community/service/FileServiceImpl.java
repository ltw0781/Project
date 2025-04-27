package com.board.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.community.domain.Files;
import com.board.community.mapper.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService{
    
    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<Files> list() throws Exception {
        List<Files> fileList = fileMapper.list();
        return fileList;
    }

    @Override
    public Files select(String id) throws Exception {
        Files file = fileMapper.select(id);
        return file;
    }

    @Override
    public int insert(Files file) throws Exception {
        int result = fileMapper.insert(file);
        return result;
    }

    @Override
    public int update(Files file) throws Exception {
        int result = fileMapper.update(file);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = fileMapper.delete(id);
        return result;
    }
    
}
