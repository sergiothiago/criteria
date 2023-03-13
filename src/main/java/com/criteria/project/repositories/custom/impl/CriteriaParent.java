package com.criteria.project.repositories.custom.impl;

import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CriteriaParent<T> {

    @PersistenceContext
    protected EntityManager em;

    protected List preparePaginationToQuery(Pageable page, TypedQuery<T> query) {
        query.getResultList().size();

        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        return query.getResultList();
    }

}
