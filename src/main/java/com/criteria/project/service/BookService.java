package com.criteria.project.service;

import com.criteria.project.builder.BookBuilder;
import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import com.criteria.project.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookBuilder builder;

    @Transactional
    public Page<BookDTO> findAllWithCriteria(BookDTO bookDTO, Pageable pageable){

        log.info("method=findAllWithCriteria  ' - BookService ");
        Page<Book> allWithCriteria = repository.findAllWithCriteria(bookDTO, pageable);
        Page<BookDTO> responseDTO = allWithCriteria.map(builder::entityToDTO);
        log.info("finish method=findAllWithCriteria  ' - BookService ");

        return responseDTO;
    }
}
