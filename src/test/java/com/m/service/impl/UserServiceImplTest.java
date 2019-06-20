package com.m.service.impl;

import com.m.entity.User;
import com.m.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    @Transactional(transactionManager = "primaryTransactionManager", propagation = Propagation.REQUIRED)
    public void testUserService() {
        User user = userService.getUserById(1);
        System.out.println(user.getName());
    }
}