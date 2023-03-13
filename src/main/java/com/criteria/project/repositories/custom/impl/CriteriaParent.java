package com.criteria.project.repositories.custom.impl;

import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CriteriaParent<T> {

    @PersistenceContext
    protected EntityManager em;

    protected int getTotalRows(Pageable page, TypedQuery<T> query) {
        int totalRows = query.getResultList().size();

        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        return totalRows;
    }
}
