package com.m.controller;

import com.m.dto.UserModel;
import com.m.entity.User;
import com.m.service.UserService;
import com.m.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public String createUser(UserModel userModel) {
        int result = userService.createUser(userModel);
        if(result > 0) {
            return "创建成功";
        }
        return "创建用户失败,请检查录入数据";
    }

    @PostMapping("/login")
    public String loginUser(UserModel userModel,
                            HttpServletRequest request) {
        User user = userService.getUserByName(userModel.getName());
        if(user != null && user.getPassword().equals(MD5Util.GetMD5Code(userModel.getPassword()))) {
            request.getSession().setAttribute("user", user.getName());
            return "登陆成功";
        }
        return "用户名或密码错误";
    }
}
