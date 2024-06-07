package com.sciGallery.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    ACTIVE(1,"Active"),
    ARCHIVED(2,"Archived"),
    UPCOMING(3,"upcoming");

    private final int code;
    private final String des;

    public static String getDesByCode(int code){
        return Stream.of(values()).filter(e->e.code==code).map(StatusEnum::getDes).findFirst().orElse("");
    }
}
