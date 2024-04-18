package com.sciGallery.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Program实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    private Integer id; //ID
    private String name; //Program名称
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
