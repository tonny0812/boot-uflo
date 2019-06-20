package com.m.config;


import com.bstek.uflo.env.EnvironmentProvider;
import com.m.utils.RequestHolderUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@Configuration
public class UfloEnvironmentConfig implements EnvironmentProvider {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    @Qualifier("sessionFactory")
    EntityManagerFactory entityManagerFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public PlatformTransactionManager getPlatformTransactionManager() {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Override
    public String getCategoryId() {
        return null;
    }

    @Override
    public String getLoginUser() {
        HttpServletRequest request = RequestHolderUtil.getThreadLocal();
        return (String) request.getSession().getAttribute("user");
    }

}