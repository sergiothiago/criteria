package com.criteria.project.repositories.custom;

import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryCustom {

    Page<Book> findAllWithCriteria(BookDTO bookDTO, Pageable page);
}
