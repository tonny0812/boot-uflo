package com.m.service;

import com.m.dto.UserModel;
import com.m.entity.User;

public interface UserService {
    User getUserById(Integer Id);
    int createUser(UserModel userModel);
    User getUserByName(String userName);

}
