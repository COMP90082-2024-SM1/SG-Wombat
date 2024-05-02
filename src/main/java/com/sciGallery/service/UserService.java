package com.sciGallery.service;

import com.sciGallery.pojo.User;

public interface UserService {


    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
