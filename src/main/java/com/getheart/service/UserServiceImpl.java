package com.getheart.service;

import com.getheart.mapper.UserLoginMapper;
import com.getheart.pojo.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Json
 * @date 2020-08-15:01
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserLoginMapper usermap;
    @Override
    public Userinfo findUserByName(String username) {
        return usermap.findUserByName(username);
    }
}
