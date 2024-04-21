package com.sciGallery;

import com.sciGallery.mapper.EmpMapper;
import com.sciGallery.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SciGalleryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testDelete(){
        empMapper.deleteById(4);
    }

    @Test
    public void testSelect(){
        Emp emp3 = empMapper.selectById(3);
        System.out.println(emp3);
    }

}
