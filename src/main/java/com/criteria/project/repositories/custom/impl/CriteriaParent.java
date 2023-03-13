package com.criteria.project.repositories.custom.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CriteriaParent<T> {

    @PersistenceContext
    protected EntityManager em;

    protected List preparePaginationToQuery(Pageable page, TypedQuery<T> query) {
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        return query.getResultList();
    }

    protected Page<T> getEntities(Pageable page, CriteriaQuery<T> cq, Long totalSize) {
        TypedQuery<T> query = em.createQuery(cq);
        List resultList = preparePaginationToQuery(page, query);
        Page<T> result = new PageImpl<>(resultList, page, totalSize);
        return result;
    }

}
