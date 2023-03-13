package com.criteria.project.repositories.custom.impl;

import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import com.criteria.project.repositories.custom.BookRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepositoryImpl extends CriteriaParent<Book> implements BookRepositoryCustom {

    @Override
    public Page<Book> findBooks(BookDTO bookDTO, Pageable page) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getAuthor())) {
            predicates.add(cb.equal(book.get("author"), bookDTO.getAuthor()));
        }
        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getTitle())) {
            predicates.add(cb.like(book.get("title"), "%" + bookDTO.getTitle() + "%"));
        }
        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getLegacyId())) {
            predicates.add(cb.equal(book.get("legacyId"), bookDTO.getLegacyId()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Book> query = em.createQuery(cq);
        List resultList = preparePaginationToQuery(page, query);

        Page<Book> result = new PageImpl<>(resultList, page, resultList.size());
        return result;
    }


}
