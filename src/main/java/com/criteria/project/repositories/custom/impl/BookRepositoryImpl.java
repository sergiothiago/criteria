package com.criteria.project.repositories.custom.impl;

import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import com.criteria.project.repositories.custom.BookRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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

        filterAttributesFromEntity(bookDTO, cb, book, predicates);

        cq.where(predicates.toArray(new Predicate[0]));

        Long totalSize = getTotalSize(cb, Book.class);

        Page<Book> result = getEntities(page, cq, totalSize);
        return result;
    }

    private static void filterAttributesFromEntity(BookDTO bookDTO, CriteriaBuilder cb, Root<Book> book, List<Predicate> predicates) {
        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getAuthor())) {
            predicates.add(cb.equal(book.get("author"), bookDTO.getAuthor()));
        }
        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getTitle())) {
            predicates.add(cb.like(book.get("title"), "%" + bookDTO.getTitle() + "%"));
        }
        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getLegacyId())) {
            predicates.add(cb.equal(book.get("legacyId"), bookDTO.getLegacyId()));
        }
    }


}
