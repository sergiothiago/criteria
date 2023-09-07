package com.criteria.project.repositories.custom.impl;

import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import com.criteria.project.repositories.custom.BookRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepositoryImpl extends CriteriaParent<Book, BookDTO> implements BookRepositoryCustom {


    @Override
    protected void doJoins(Root root) {

    }

    @Override
    public Page<Book> findAllWithCriteria(BookDTO bookDTO, Pageable pageable) {
        return super.findAllWithCriteriaParent(bookDTO,pageable);
    }

    @Override
    protected void filterAttributesFromEntity(BookDTO bookDTO, CriteriaBuilder cb, Root<Book> root, List<Predicate> predicates) {

        if (Objects.isNull(bookDTO)) {
            return;
        }

        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getId())) {
            predicates.add(cb.equal(root.get("id"), bookDTO.getId()));
        }

        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getTitle())) {
            predicates.add(cb.like(root.get("title"), "%" + bookDTO.getTitle() + "%"));
        }

        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getLegacyId())) {
            predicates.add(cb.equal(root.get("legacyId"), bookDTO.getLegacyId()));
        }

        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getAuthorId())) {
            predicates.add(cb.equal(root.get("author").get("id"), bookDTO.getAuthorId()));
        }

        if (Objects.nonNull(bookDTO) && Objects.nonNull(bookDTO.getAuthorLegacyId())) {
            predicates.add(cb.equal(root.get("author").get("legacyId"), bookDTO.getAuthorLegacyId()));
        }
    }
}
