package com.getheart.config;

import com.getheart.pojo.Userinfo;
import com.getheart.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Json
 * @date 2020-08-11:22
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行了授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        //拿到下面的userinfo对象
        Userinfo userinfo =(Userinfo) subject.getPrincipal();

        info.addStringPermission(userinfo.getPerms());


        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("执行了认证");

        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
        //连接数据库
        Userinfo user = userService.findUserByName(usertoken.getUsername());

        if (user == null){
            return null;
        }

        return new SimpleAuthenticationInfo(
                 user,
                 user.getPassword(),
                 this.getName());
    }
}
