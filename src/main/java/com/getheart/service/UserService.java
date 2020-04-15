package com.getheart.service;

import com.getheart.pojo.Userinfo;

/**
 * @author Json
 * @date 2020-08-15:00
 */
public interface UserService {

    Userinfo findUserByName(String username);
}
