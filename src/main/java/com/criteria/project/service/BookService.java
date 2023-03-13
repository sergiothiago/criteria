package com.criteria.project.service;

import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import com.criteria.project.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findBooks(BookDTO bookDTO){
        return bookRepository.findBooks(bookDTO);
    }

    public Page<Book> findBooksPageable(BookDTO bookDTO, Pageable pageable){
        return bookRepository.findBooksPageable(bookDTO, pageable);
    }
}
