package com.sciGallery.mapper;


import com.sciGallery.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User getByUserNameAndPassword(User user);
}
