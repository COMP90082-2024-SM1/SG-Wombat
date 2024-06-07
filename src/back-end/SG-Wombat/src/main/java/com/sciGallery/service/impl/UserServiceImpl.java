package com.sciGallery.service.impl;

import com.sciGallery.mapper.UserMapper;
import com.sciGallery.pojo.User;
import com.sciGallery.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.getByUserNameAndPassword(user);
    }
}
