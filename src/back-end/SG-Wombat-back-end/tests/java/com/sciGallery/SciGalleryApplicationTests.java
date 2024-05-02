package com.sciGallery;

import com.sciGallery.pojo.Program;
import com.sciGallery.pojo.User;
import com.sciGallery.service.ProgramService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class SciGalleryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ProgramService programService;

    @Test
    public void testProgramList(){
        programService.list();
    }

    @Test
    public void testProgramDelete(){
        programService.delete(1);
    }

    @Test
    public void testProgramAdd(){
        Program programOne = new Program();
        programOne.setName("program1");
        programOne.setMaxCap(10);
        programOne.setTechReq("Ipad");
        programOne.setCostPerson(new BigDecimal(10));
        programOne.setStatus(1);
        programOne.setDuration("12:00-13:00");
        programOne.setMonday(0);
        programOne.setTuesday(0);
        programOne.setWednesday(1);
        programOne.setThursday(0);
        programOne.setFriday(0);
        programOne.setDescription("None");
        programService.add(programOne);
    }

    @Test
    public void testProgramSearch(){
        programService.getById(1);
    }

    @Test
    public void testProgramUpdate(){
        Program programOne = new Program();
        programOne.setProgId(1);
        programOne.setName("program2");
        programOne.setMaxCap(10);
        programOne.setTechReq("Ipad");
        programOne.setCostPerson(new BigDecimal(10));
        programOne.setStatus(1);
        programOne.setDuration("12:00-13:00");
        programOne.setMonday(0);
        programOne.setTuesday(0);
        programOne.setWednesday(1);
        programOne.setThursday(0);
        programOne.setFriday(0);
        programOne.setDescription("None");
        programService.update(programOne);
    }


}
