package com.getheart.controller;

import com.getheart.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Json
 * @date 2020-08-11:01
 */
@Controller
public class LongControll {

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public String index(){

        return "success";
    }
    @RequestMapping("toLogin")
    public String toLogin(){

        return "views/login";
    }
    @RequestMapping("login")
    public String Login(String username, String password, Model model){

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "success";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误！");
            return "views/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误！");
            return "views/login";
        }

    }


    @GetMapping("vip1/{id}")
    public String vip1(@PathVariable("id") Integer id){

        return "views/vip1/"+ id;
    }
    @GetMapping("vip2/{id}")
    public String vip2(@PathVariable("id") Integer id){

        return "views/vip2/"+ id;
    }
    @GetMapping("vip3/{id}")
    public String vip3(@PathVariable("id") Integer id){

        return "views/vip3/"+ id;
    }

    @RequestMapping("unauthorize")
    @ResponseBody
    public String unauthorize(){
        return "未授权！！";
    }

    @RequestMapping("logout")
    public String loginout(){

        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return "views/login";
    }
}
