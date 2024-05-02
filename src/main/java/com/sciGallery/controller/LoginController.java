package com.sciGallery.controller;


import com.sciGallery.pojo.Result;
import com.sciGallery.pojo.User;
import com.sciGallery.service.UserService;
import com.sciGallery.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("Login: {}", user);
        User u = userService.login(user);

        //登陆成功，下发令牌
        if (u != null) {


            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());

            String jwt = JwtUtils.generateJWT(claims);
            return Result.success(jwt);
        }

        //登录失败，返回错误信息
        return Result.error("Username or Password incorrect");
    }
}
