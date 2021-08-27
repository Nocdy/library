package com.nocdy.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nocdy.web.dao.BookMapper;
import com.nocdy.web.entity.Book;
import com.nocdy.web.service.BookService;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
