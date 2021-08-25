package com.nocdy.web.controller;

import com.nocdy.web.entity.User;
import com.nocdy.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = {"/","/login"})
    public String login(){
        return  "index";
    }

    @GetMapping(value = "/registry")
    public String toRegistry(){
        return "user/registry";
    }

    @PostMapping(value = "/registry")
    public @ResponseBody Object registry(@RequestBody User user){

        if(user.getPassword()== user.getConfirmPassword()){
            userService.save(user);
            return "success";
        }
        else{
            return "error";
        }
    }


}
