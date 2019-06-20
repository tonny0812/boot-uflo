package com.m.service.impl;

import com.m.model.User;
import com.m.repository.UserRepository;
import com.m.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    UserRepository userRepository;

    @Override
    @Transactional
    public User getUserById(Integer Id) {
        if(Id == null) {
            throw new RuntimeException("bad request");
        }
//        User user = userRepository.getOne(Id);
//        if(user.getId() != null) {
//            return user;
//        }else{
//            return null;
//        }
        return null;
    }
}
