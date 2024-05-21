package com.sciGallery.controller;

import com.sciGallery.pojo.Program;
import com.sciGallery.pojo.Result;
import com.sciGallery.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Program controller
 */

@Slf4j
@RestController
public class ProgramController {

    @Autowired
    private ProgramService programService;


    /**
     * 查询program
     * @return
     */

    //@RequestMapping(value = "/progs", method = RequestMethod.GET)
    @GetMapping("/progs")
    public Result list(String name){
        log.info("查询全部program数据");

        List<Program> programList = programService.list(name);

        return Result.success(programList);
    }






    @GetMapping("/progs/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询program信息， id：{}", id);
        Program program = programService.getById(id);
        return Result.success(program);
    }

    /**
     * 根据id删除对应program
     * @param id
     */
    @DeleteMapping("/progs/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除对应program");

        programService.delete(id);

        return Result.success();
    }


    /**
     * 新增program
     * @param program
     * @return
     */
    @PostMapping("/progs")
    public Result add(@RequestBody Program program){
        log.info("新增Program：{}", program);

        programService.add(program);

        return Result.success();
    }



    @PutMapping("/progs")
    public Result update(@RequestBody Program program){
        log.info("根据id更新program");

        programService.update(program);

        return Result.success();

    }



}
