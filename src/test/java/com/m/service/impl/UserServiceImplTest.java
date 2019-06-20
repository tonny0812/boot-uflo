package com.m.service.impl;

import com.m.model.User;
import com.m.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void testUserService() {
        User user = userService.getUserById(1);
        System.out.println(user.getName());
    }
}