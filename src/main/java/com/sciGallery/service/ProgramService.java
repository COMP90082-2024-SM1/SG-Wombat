package com.sciGallery.service;

import com.sciGallery.pojo.Program;

import java.util.List;

/**
 * 部门管理
 */
public interface ProgramService {
    /**
     * 查询全部program数据
     * @return
     */
    List<Program> list();

    /**
     * 根据id删除对应program
     * @param id
     */
    void delete(Integer id);

    /**
     * 新增program
     * @param program
     */
    void add(Program program);


    /**
     * 根据id查询对应program
     * @param id
     */
    Program getById(Integer id);


    /**
     * 根据id更新program
     * @param program
     */
    void update(Program program);
}
