package com.example.EstateEase;

import com.example.EstateEase.model.Property;
import com.example.EstateEase.model.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class PropertySpecification implements Specification<Property> {
    private final SearchCriteria searchCriteria;

    public PropertySpecification(final SearchCriteria searchCriteria){
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Property> root, CriteriaQuery<?> query, CriteriaBuilder cb){
        List<Predicate> predicates = new ArrayList<>();

        if(searchCriteria.getLocation() != null)
            predicates.add(cb.equal(root.get("location"), searchCriteria.getLocation()));

        if(searchCriteria.getMinPrice() != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), searchCriteria.getMinPrice()));

        if(searchCriteria.getMaxPrice() != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), searchCriteria.getMaxPrice()));

        if(searchCriteria.getMinSize() != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("size"), searchCriteria.getMinSize()));

        if(searchCriteria.getMaxSize() != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("size"), searchCriteria.getMaxSize()));

        if(searchCriteria.getMinRooms() != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("rooms"), searchCriteria.getMinRooms()));

        if(searchCriteria.getMaxRooms() != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("rooms"), searchCriteria.getMaxRooms()));

        if (searchCriteria.getListing() != null) {
            predicates.add(cb.equal(root.get("listing"), searchCriteria.getListing()));
        }

        if (searchCriteria.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), searchCriteria.getStatus()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
