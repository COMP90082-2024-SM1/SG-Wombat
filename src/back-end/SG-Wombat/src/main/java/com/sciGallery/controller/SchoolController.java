package com.sciGallery.controller;


import com.sciGallery.pojo.PageBean;
import com.sciGallery.pojo.Program;
import com.sciGallery.pojo.Result;
import com.sciGallery.pojo.School;
import com.sciGallery.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/schools")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       String name){
        log.info("分页查询, 参数：{},{}", page, name);
        PageBean pageBean = schoolService.page(page, name);
        return Result.success(pageBean);
    }


    @DeleteMapping("/schools/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除对应school");

        schoolService.delete(id);

        return Result.success();
    }



    @PostMapping("/schools")
    public Result add(@RequestBody School school){
        log.info("新增School：{}", school);

        schoolService.add(school);

        return Result.success();
    }


    @GetMapping("/schools/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询school信息， id：{}", id);
        School school = schoolService.getById(id);
        return Result.success(school);
    }




    @PutMapping("/schools")
    public Result update(@RequestBody School school){
        log.info("根据id更新school");

        schoolService.update(school);

        return Result.success();

    }

}
