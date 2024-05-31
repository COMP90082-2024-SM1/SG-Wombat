package com.sciGallery.service;

import com.sciGallery.pojo.Booking;
import com.sciGallery.pojo.PageBean;

public interface BookingService {



    void add(Booking booking);

    void delete(Integer id);




    /**
     * 分页查询
     * @param page
     * @return
     */
    PageBean page(Integer page, Integer requestConfirmed);


    Booking getById(Integer id);

    void update(Booking booking);
}
