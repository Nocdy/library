package com.nocdy.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nocdy.web.entity.Book;
import com.nocdy.web.entity.Record;
import com.nocdy.web.entity.User;
import com.nocdy.web.service.BookService;
import com.nocdy.web.service.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {

    @Resource
    BookService bookService;

    @Resource
    RecordService recordService;

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


    @GetMapping(value = "/book/borrow/{id}")
    public @ResponseBody Object borrowBook(HttpSession session,
                                           @PathVariable("id") Integer id){
        Record record=new Record();
        Book book=bookService.getById(id);
        User user= (User) session.getAttribute("User");
        Calendar borrowTime=Calendar.getInstance();
        Calendar returnTime=Calendar.getInstance();
        returnTime.add(Calendar.MONTH,1);
        record.setBorrowTime(borrowTime.getTime());
        record.setReturnTime(returnTime.getTime());
        record.setBook(id);
        record.setUser(user.getId());
        record.setOutOfTime(false);
        record.setStatus(true);
        book.setCount(book.getCount()-1);
        bookService.updateById(book);
        recordService.save(record);
        return "success";
    }


    @GetMapping(value = "book/return/{id}")
    public @ResponseBody Object returnBook(@PathVariable("id") Integer id){
        Record record=recordService.getById(id);
        Date now=Calendar.getInstance().getTime();
        if(record.getReturnTime().getTime()<now.getTime()){
            record.setOutOfTime(true);
        }
        record.setStatus(false);
        recordService.updateById(record);
        return "success";
    }

}
