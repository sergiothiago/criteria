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
public class BookRepositoryImpl extends CriteriaParent implements BookRepositoryCustom {

    @Override
    public List<Book> findBooks(BookDTO bookDTO) {
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
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public Page<Book> findBooksPageable(BookDTO bookDTO, Pageable page) {
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
        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Book> query = em.createQuery(cq);
        int totalRows = query.getResultList().size();

        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        Page<Book> result = new PageImpl<Book>(query.getResultList(), page, totalRows);

        return result;
    }
}
