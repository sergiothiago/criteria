package com.criteria.project.controller;

import com.criteria.project.dto.BookDTO;
import com.criteria.project.dto.PageInfoDTO;
import com.criteria.project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController extends MainController {

    @Autowired
    private BookService bookService;
    
    @GetMapping("/search")
    public Page<BookDTO> findAllWithCriteria(BookDTO bookDTO, PageInfoDTO pageInfoDTO){
        Pageable pageable = setDefaultPageableParameters(pageInfoDTO);
        return bookService.findAllWithCriteria(bookDTO, pageable);
    }

}
