package com.getheart.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Json
 * @date 2020-08-11:04
 */
@Configuration
public class ShiroConfig {

    /**
     * ShiroFilterFactoryBean
     * @param defaultWebManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFactory(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebManager){


        ShiroFilterFactoryBean shiroFactory = new ShiroFilterFactoryBean( );
        shiroFactory.setSecurityManager(defaultWebManager);

        /**
         *  添加shrio的内置过滤器
         * anon: 无需认证就可以访问
         * authc: 必须认证才能访问
         * user： 必须拥有 记住我 功能才能用
         * perms: 拥有对某一资源的权限才能访问
         * role ：拥有某个角色才能访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/index", "anon");
        filterMap.put("/vip1/**", "authc");
        filterMap.put("/vip2/**", "authc");
        filterMap.put("/vip3/**", "authc");
        //登出
        filterMap.put("/logout/","logout");

        filterMap.put("/vip1/**", "perms[user:admin]");
        filterMap.put("/vip2/**", "perms[user:vip2]");

        shiroFactory.setFilterChainDefinitionMap(filterMap);

        shiroFactory.setUnauthorizedUrl("/unauthorize");


        //设置登录路径
        shiroFactory.setLoginUrl("/toLogin");


        return shiroFactory;
    }

    /**
     * DafaultWebSecurityMannager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getdefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);

        return securityManager;

    }
    /**
     * 创建 Realm 对象， 需要自定义
     * @return
     */
    @Bean
    public UserRealm userRealm(){
       return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
