package com.m.service.impl;

import com.m.dto.UserModel;
import com.m.entity.User;
import com.m.repository.UserRepository;
import com.m.service.UserService;
import com.m.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
    protected Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    /**
     * 通过用户id查询用户信息
     * @param Id
     * @return
     */
    @Override
    @Transactional(transactionManager = "primaryTransactionManager", propagation = Propagation.REQUIRED)
    public User getUserById(Integer Id) {
        if(Id == null) {
            throw new RuntimeException("bad request");
        }
        User user = userRepository.getOne(Id);
        if(user.getId() != null) {
            return user;
        }else{
            return null;
        }
    }

    /**
     * 创建用户
     * @param userModel
     * @return
     */
    @Override
    @Transactional(transactionManager = "primaryTransactionManager", propagation = Propagation.REQUIRED)
    public int createUser(UserModel userModel) {
        User user = new User();
        String password = MD5Util.GetMD5Code(userModel.getPassword());
        BeanUtils.copyProperties(userModel, user);
        user.setPassword(password);
        User result = userRepository.saveAndFlush(user);
        logger.info("user result name: " + result.getName());
        return result.getId();
    }

    /**
     * 通过用户name查询用户信息
     * @param userName
     * @return
     */
    @Override
    @Transactional(transactionManager = "primaryTransactionManager", propagation = Propagation.REQUIRED)
    public User getUserByName(String userName) {
        User user = userRepository.findUserByName(userName);
        return user;
    }
}
