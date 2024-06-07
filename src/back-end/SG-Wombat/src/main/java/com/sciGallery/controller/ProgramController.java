package com.sciGallery.controller;

import com.sciGallery.config.WeekEnum;
import com.sciGallery.pojo.Program;
import com.sciGallery.pojo.Result;
import com.sciGallery.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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
        programList.forEach(e->{

            String days = "";
            if (Objects.nonNull(e.getMonday())&&1==e.getMonday()){
                days+="Monday,";
            }
            if (Objects.nonNull(e.getTuesday())&&1==e.getTuesday()){
                days+="Tuesday,";
            }
            if (Objects.nonNull(e.getWednesday())&&1==e.getWednesday()){
                days+="Wednesday,";
            }
            if (Objects.nonNull(e.getThursday())&&1==e.getThursday()){
                days+="Thursday,";
            }
            if (Objects.nonNull(e.getFriday())&&1==e.getFriday()){
                days+="Friday";
            }

            e.setAvaliableDays(days);
        });

        return Result.success(programList);
    }






    @GetMapping("/progs/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工信息， id：{}", id);
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
        if (!CollectionUtils.isEmpty(program.getWorkDays())){
            program.getWorkDays().forEach(day->{

                if (WeekEnum.MONDAY.getDes().equals(day)){
                    program.setMonday(1);
                }
                if (WeekEnum.TUESDAY.getDes().equals(day)){
                    program.setTuesday(1);
                }
                if (WeekEnum.WEDNESDAY.getDes().equals(day)){
                    program.setWednesday(1);
                }
                if (WeekEnum.THURSDAY.getDes().equals(day)){
                    program.setThursday(1);
                }
                if (WeekEnum.FRIDAY.getDes().equals(day)){
                    program.setFriday(1);
                }
            });

        }
        programService.add(program);

        return Result.success();
    }



    @PutMapping("/progs")
    public Result update(@RequestBody Program program){
        log.info("根据id更新program");
        program.getWorkDays().forEach(day->{
            if (WeekEnum.MONDAY.getDes().equals(day)){
                program.setMonday(1);
            }
            if (WeekEnum.TUESDAY.getDes().equals(day)){
                program.setTuesday(1);
            }
            if (WeekEnum.WEDNESDAY.getDes().equals(day)){
                program.setWednesday(1);
            }
            if (WeekEnum.THURSDAY.getDes().equals(day)){
                program.setThursday(1);
            }
            if (WeekEnum.FRIDAY.getDes().equals(day)){
                program.setFriday(1);
            }
        });

        programService.update(program);

        return Result.success();

    }



}
