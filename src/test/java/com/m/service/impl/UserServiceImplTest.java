package com.m.service.impl;

import com.bstek.uflo.model.ContextProperty;
import com.m.entity.User;
import com.m.service.UserService;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    @Transactional(transactionManager = "primaryTransactionManager", propagation = Propagation.REQUIRED)
    public void testUserService() {
        User user = userService.getUserById(1);
        System.out.println(user.getName());
    }

    @Test
    @Transactional(transactionManager = "platformTransactionManager", propagation = Propagation.REQUIRED)
    public void testUser() {
        List<ContextProperty> list = sessionFactory.getCurrentSession()
                .createQuery("from "+ContextProperty.class.getName()+" as p where p.key=:key")
                .setParameter("key", "dbid")
                .setLockMode("p", LockMode.PESSIMISTIC_WRITE).list();
//        sessionFactory.getProperties().forEach((k,v) -> System.out.println(k + " : " + v));
    }

}