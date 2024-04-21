package com.sciGallery.mapper;

import com.sciGallery.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    @Delete("delete from tb_emp where id = #{id}")
    public void deleteById(Integer id);


    @Select("select * from tb_emp where id = #{id}")
    public Emp selectById(Integer id);
}
