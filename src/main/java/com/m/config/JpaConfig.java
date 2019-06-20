package com.m.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@EnableJpaRepositories(basePackages="com.m.repository")
//@EntityScan(basePackages={"com.m.model"})
public class JpaConfig {
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private JpaProperties jpaProperties;
//    @Autowired
//    private HibernateProperties hibernateProperties;
//
//    @Primary
//    @Bean(name = "primaryEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dataSource)
//                //设置entity所在位置
//                .packages("com.m.model")
//                .persistenceUnit("primaryPersistenceUnit")
//                .properties(getVendorProperties())
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "primaryJpaTransactionManager")
//    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
//    }
//
//    private Map<String, Object> getVendorProperties() {
//        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
//    }
}
