package com.criteria.project.repositories;

import com.criteria.project.repositories.custom.BookRepositoryCustom;
import com.criteria.project.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
}
