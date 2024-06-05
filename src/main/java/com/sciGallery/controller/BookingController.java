package com.sciGallery.controller;


import com.sciGallery.pojo.*;
import com.sciGallery.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @GetMapping("/booking")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       Integer requestConfirmed){
        log.info("分页查询, 参数：{},{}", page, requestConfirmed);
        PageBean pageBean = bookingService.page(page, requestConfirmed);
        return Result.success(pageBean);
    }





    @DeleteMapping("/booking/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除对应booking");

        bookingService.delete(id);

        return Result.success();
    }




    @PostMapping("/booking")
    public Result add(@RequestBody Booking booking){
        log.info("新增booking：{}", booking);

        bookingService.add(booking);

        return Result.success();
    }



    @GetMapping("/booking/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询booking信息， id：{}", id);
        Booking booking = bookingService.getById(id);
        return Result.success(booking);
    }




    @PutMapping("/booking")
    public Result update(@RequestBody Booking booking){
        log.info("根据id更新booking");

        bookingService.update(booking);

        return Result.success();

    }

}
