package com.sciGallery.mapper;


import com.sciGallery.pojo.School;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SchoolMapper {


    List<School> list(String name);

    @Delete("DELETE from tb_school where school_id = #{id}")
    void deleteById(Integer id);


    @Insert("insert into tb_school(name, post_code, icsea, abn_num)" +
            "values (#{name},#{postCode},#{icsea}, #{abnNum})")
    void insert(School school);


    @Select("select * from tb_school where school_id = #{id}")
    School getById(Integer id);



    void update(School school);
}
