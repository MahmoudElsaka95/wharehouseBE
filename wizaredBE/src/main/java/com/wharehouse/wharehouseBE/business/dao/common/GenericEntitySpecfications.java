/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wharehouse.wharehouseBE.business.dao.common;

import com.wharehouse.wharehouseBE.model.enums.SortTypeEnum;
import com.wharehouse.wharehouseBE.model.pojos.SearchParPojo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.domain.Specification;


public abstract class GenericEntitySpecfications<T extends Serializable> {

    public Specification<T> filterEntityList(SearchParPojo searchPar) {
        Class<T> domainEntityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericEntitySpecfications.class);

        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (searchPar != null) {
                // append filters
                AbstractSpecification.appendFilterToQuery(domainEntityType, searchPar, root, predicates, cb);

                //sort data
                if (searchPar.getSortObject() != null && searchPar.getSortObject().getFieldName() != null) {
                    if (SortTypeEnum.ASC.getSortType().equalsIgnoreCase(searchPar.getSortObject().getSortDirection())) {
                        query.orderBy(cb.asc(AbstractSpecification.getPathExpression(root, searchPar.getSortObject().getFieldName())));
                    } else if (SortTypeEnum.DESC.getSortType().equalsIgnoreCase(searchPar.getSortObject().getSortDirection())) {
                        query.orderBy(cb.desc(AbstractSpecification.getPathExpression(root, searchPar.getSortObject().getFieldName())));
                    }
                }

            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));

        };
    }

}
