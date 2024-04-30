package com.sciGallery.mapper;

import com.sciGallery.pojo.Program;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Program管理
 */
@Mapper
public interface ProgramMapper {
    /**
     * 查询全部program
     * @return
     */

    @Select("SELECT * from tb_program")
    List<Program> list();


    /**
     * 根据id删除对应program
     * @param id
     */
    @Delete("DELETE from tb_program where prog_id = #{id}")
    void deleteById(Integer id);


    /**
     * 新建program
     * @param program
     */
    @Insert("INSERT into tb_program(name, max_cap, tech_req, cost_person, duration, status, Monday, Tuesday, Wednesday, Thursday, Friday, description) " +
            "values(#{name}, #{maxCap}, #{techReq}, #{costPerson}, #{duration}, #{status}, #{Monday}, #{Tuesday}, #{Wednesday}, #{Thursday}, #{Friday}, #{description})")
    void insert(Program program);


    /**
     * 根据id查询program
     * @param id
     * @return
     */

    @Select("select * from tb_program where prog_id = #{id}")
    Program getById(Integer id);


    /**
     * 更新program
     * @param program
     */
    void update(Program program);
}
