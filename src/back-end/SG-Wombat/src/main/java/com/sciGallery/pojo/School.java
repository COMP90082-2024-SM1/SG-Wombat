package com.sciGallery.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * School实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    private int schoolId;
    private String name;
    private String postCode;
    private String icsea;
    private String abnNum;

}
