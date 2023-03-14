package com.criteria.project.service;

import com.criteria.project.dto.BookDTO;
import com.criteria.project.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Page<BookDTO> findBooks(BookDTO bookDTO, Pageable pageable){
        return bookRepository.findBooks(bookDTO, pageable);
    }
}
