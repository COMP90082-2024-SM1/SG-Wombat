package com.sciGallery.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sciGallery.mapper.SchoolMapper;
import com.sciGallery.pojo.PageBean;
import com.sciGallery.pojo.School;
import com.sciGallery.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;



    @Override
    public PageBean page(Integer page, String name) {
        PageHelper.startPage(page, 10);

        List<School> schoolList = schoolMapper.list(name);
        Page<School> p = (Page<School>) schoolList;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(Integer id) {
        schoolMapper.deleteById(id);
    }

    @Override
    public void add(School school) {
        schoolMapper.insert(school);
    }

    @Override
    public School getById(Integer id) {
        return schoolMapper.getById(id);
    }

    @Override
    public void update(School school) {
        schoolMapper.update(school);
    }
}
