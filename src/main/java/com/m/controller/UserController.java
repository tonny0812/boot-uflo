package com.m.controller;

import com.m.dto.UserModel;
import com.m.entity.User;
import com.m.service.UserService;
import com.m.utils.CodeMsg;
import com.m.utils.MD5Util;
import com.m.utils.RequestHolderUtil;
import com.m.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 创建用户
     * @param userModel
     * @return
     */
    @PostMapping("/create")
    public ResultUtil<String> createUser(UserModel userModel) {
        int result = userService.createUser(userModel);
        if(result > 0) {
            return ResultUtil.success("创建成功");
        }
        return ResultUtil.error(CodeMsg.USER_CREATE_ERROR);
    }

    /**
     * 用户登录
     * @param userModel
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResultUtil<String> loginUser(UserModel userModel,
                            HttpServletRequest request) {
        User user = userService.getUserByName(userModel.getName());
        // 登录成功将name值保存session
        if(user != null && user.getPassword().equals(MD5Util.GetMD5Code(userModel.getPassword()))) {
            request.getSession().setAttribute("user", user.getName());
            return ResultUtil.success("登陆成功");
        }
        return ResultUtil.error(CodeMsg.COOKIE_ERROR);
    }
}
