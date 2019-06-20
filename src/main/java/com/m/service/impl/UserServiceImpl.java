package com.m.service.impl;

import com.m.dto.UserModel;
import com.m.entity.User;
import com.m.repository.UserRepository;
import com.m.service.UserService;
import com.m.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

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
//        return null;
    }

    @Override
    @Transactional(transactionManager = "primaryTransactionManager", propagation = Propagation.REQUIRED)
    public int createUser(UserModel userModel) {
        User user = new User();
        String password = MD5Util.GetMD5Code(userModel.getPassword());
        BeanUtils.copyProperties(userModel, user);
        user.setPassword(password);
        User result = userRepository.saveAndFlush(user);
        return result.getId();
    }

    @Override
    @Transactional(transactionManager = "primaryTransactionManager", propagation = Propagation.REQUIRED)
    public User getUserByName(String userName) {
        User user = userRepository.findUserByName(userName);
        return user;
    }
}
