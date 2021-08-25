package com.nocdy.web.controller;



import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nocdy.web.entity.User;
import com.nocdy.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/")
    public String toLogin(){
        return  "index";
    }

    @PostMapping(value = "/login")
    public @ResponseBody Object login(@RequestParam("name") String name,
                                      @RequestParam("password") String password){

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user_name",name)
                .eq("user_password",password);
        User user=userService.getOne(queryWrapper);
        if(user==null)
            return "check your info";
        else
            return "login success";
    }

    @GetMapping(value = "/registry")
    public String toRegistry(){
        return "user/registry";
    }

    @PostMapping(value = "/registry")
    public @ResponseBody Object registry(@RequestParam("name") String username,
                                         @RequestParam("password") String password,
                                         @RequestParam("confirmPassword") String confirmPassword){

        User user=new User();
        user.setName(username);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        if(user.getPassword().equals(user.getConfirmPassword())){
            userService.save(user);
            return "success";
        }
        else{
            return "failed";
        }
    }

}
