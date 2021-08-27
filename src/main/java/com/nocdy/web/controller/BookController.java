package com.nocdy.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nocdy.web.entity.Book;
import com.nocdy.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/book")
    public String toBook(Model model){

        List<Book> list=bookService.list();
        model.addAttribute("BookList",list);
        return "book/surfing-book";
    }

    @GetMapping("/book/search")
    public @ResponseBody Object searchBookByType(@RequestParam("name") String name,
                                                 @RequestParam("type") String type){
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        List<Book> list;
        if(name==null) {
            if(type.equals("全部")){
                list=bookService.list();
            }
            else {
                queryWrapper
                        .eq("book_type", type);
            }
        }
        else{
            if(type.equals("全部")){
                queryWrapper
                        .like("book_name",name);
            }
            else {
            queryWrapper
                    .like("book_name",name)
                    .eq("book_type",type);
            }
        }
        list = bookService.list(queryWrapper);
        return list;
    }





}
