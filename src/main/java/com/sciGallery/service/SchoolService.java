package com.sciGallery.service;

import com.sciGallery.pojo.PageBean;
import com.sciGallery.pojo.School;

public interface SchoolService {


    /**
     * 分页查询
     * @param page
     * @return
     */
    PageBean page(Integer page, String name);


    /**
     * 根据id删除对应school
     * @param id
     */
    void delete(Integer id);


    /**
     * 新增school
     * @param school
     */
    void add(School school);


    /**
     * 根据id查询school
     * @param id
     * @return
     */
    School getById(Integer id);


    /**
     * 根据id更新school
     * @param school
     */
    void update(School school);
}
