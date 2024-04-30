package com.sciGallery.service.impl;

import com.sciGallery.mapper.ProgramMapper;
import com.sciGallery.pojo.Program;
import com.sciGallery.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramMapper programMapper;


    @Override
    public List<Program> list() {
        return programMapper.list();
    }

    @Override
    public void delete(Integer id) {
        programMapper.deleteById(id);
    }

    @Override
    public void add(Program program) {
        programMapper.insert(program);
    }

    @Override
    public Program getById(Integer id) {
        return programMapper.getById(id);
    }

    @Override
    public void update(Program program) {
        programMapper.update(program);
    }
}
