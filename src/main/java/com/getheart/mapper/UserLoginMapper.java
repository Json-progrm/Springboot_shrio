package com.getheart.mapper;

import com.getheart.pojo.Userinfo;
import org.springframework.stereotype.Repository;

/**
 * @author Json
 * @date 2020-08-14:51
 */
@Repository
public interface UserLoginMapper {


    Userinfo findUserByName(String username);
}
