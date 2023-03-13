package com.criteria.project.repositories.custom;

import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookRepositoryCustom {

    List<Book> findBooks(BookDTO bookDTO);

    Page<Book> findBooksPageable(BookDTO bookDTO, Pageable page);
}
