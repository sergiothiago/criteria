package com.criteria.project.repositories.custom.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class CriteriaParent<T, S> {

    @PersistenceContext
    protected EntityManager em;

    protected List<T> preparePaginationToQuery(Pageable page, TypedQuery<T> query) {
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        return query.getResultList();
    }

    protected Page<T> getEntities(Pageable page, CriteriaQuery<T> cq, Long totalSize) {
        TypedQuery<T> query = em.createQuery(cq);
        List<T> resultList = preparePaginationToQuery(page, query);
        Page<T> result = new PageImpl<>(resultList, page, totalSize);
        return result;
    }

    protected abstract void doJoins(Root<T> root);

    protected abstract void filterAttributesFromEntity(S paramsDto,
                                           CriteriaBuilder cb,
                                           Root<T> root,
                                           List<Predicate> predicates);

    /**
     * Count para buscar a quantidade de itens no banco de dados.
     * Note que o criteria query deste não é o mesmo da entidade.
     * @return
     */
    protected Long getTotalSize(S paramDto, Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> secondCriteriaQuery = cb.createQuery(Long.class);
        Root<T> root = secondCriteriaQuery.from(clazz);
        secondCriteriaQuery.select(cb.count(root));
        List<Predicate> predicates = new ArrayList<>();
        filterAttributesFromEntity(paramDto, cb, root, predicates);
        secondCriteriaQuery.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(secondCriteriaQuery).getSingleResult();
    }

    public Page<T> findAllWithCriteriaParent(S paramDTO,
                                             Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> theType = (Class<T>) (type).getActualTypeArguments()[0];

        CriteriaQuery<T> cq = cb.createQuery(theType);
        Root<T> root = cq.from(theType);
        doJoins(root);
        List<Predicate> predicates = new ArrayList<>();
        filterAttributesFromEntity(paramDTO, cb, root, predicates);
        cq.where(predicates.toArray(new Predicate[0]));
        Long totalSize = getTotalSize(paramDTO, theType);
        Page<T> result = getEntities(pageable, cq, totalSize);
        return result;
    }
