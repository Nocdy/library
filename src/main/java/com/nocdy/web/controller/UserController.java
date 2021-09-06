package com.nocdy.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nocdy.web.entity.Record;
import com.nocdy.web.entity.User;
import com.nocdy.web.service.RecordService;
import com.nocdy.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Nocdy
 */
@Controller
public class UserController {

    @Resource
    UserService userService;
    @Resource
    RecordService recordService;


    @GetMapping(value = "/")
    public String toLogin(){
        return  "index";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session){

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user_name",name)
                .eq("user_password",password);
        User user=userService.getOne(queryWrapper);
        if(user==null){
            return "redirect:error/404";
        }
        else{
            session.setAttribute("User",user);
            return "redirect:/book";
        }
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
            try {
                userService.save(user);
                return "success";
            }
            catch (Exception e){
                return e.toString();
            }
        }
        else{
            return "failed";
        }
    }

    @GetMapping(value = "/info")
    public @ResponseBody Object checkBorrowBook(HttpSession session){
        User user=(User) session.getAttribute("User");
        QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user",user.getId())
                .eq("status",true);
        List<Record> recordList=recordService.list(queryWrapper);
        return recordList;
    }




}
