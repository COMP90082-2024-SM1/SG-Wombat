package com.sciGallery.mapper;


import com.sciGallery.pojo.Booking;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookingMapper {

    void insert(Booking booking);


    @Delete("DELETE from tb_booking where booking_id = #{id}")
    void deleteById(Integer id);

    List<Booking> list(Integer requestConfirmed);


    Booking selectById(Integer id);

    void update(Booking booking);
}

