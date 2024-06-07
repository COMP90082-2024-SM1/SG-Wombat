package com.sciGallery.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Program实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    private int progId;
    private String name;
    private int maxCap;
    private String techReq;
    private BigDecimal costPerson;
    private String duration;
    private Integer status;
    private Integer Monday;
    private Integer Tuesday;
    private Integer Wednesday;
    private Integer Thursday;
    private Integer Friday;
    private String description;
    private String category;
    private List<String> workDays;
    private String avaliableDays;
    private String feeType;

}
