package com.criteria.project.controller;

import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import com.criteria.project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/pageable")
    public Page<Book> findBooks(BookDTO bookDTO, Integer page, Integer size){
        if(Objects.isNull(page)) {
            page = 0;
        }
        if(Objects.isNull(size)) {
            size = 50;
        }

        Pageable pageable = PageRequest.of(page,size);

        return bookService.findBooks(bookDTO, pageable);
    }

}
