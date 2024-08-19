package com.example.EstateEase.service;

import com.example.EstateEase.PropertySpecification;
import com.example.EstateEase.model.Property;
import com.example.EstateEase.model.SearchCriteria;
import com.example.EstateEase.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public Property listProperty(Property property){
        return propertyRepository.save(property);
    }

    public List<Property> getPropertiesByUserId(Long userId){
        return propertyRepository.findByUserId(userId);
    }
    public Optional<Property> findById(Long id) {
        return propertyRepository.findById(id);
    }

//    public List<Property> searchProperties(SearchCriteria searchCriteria) {
//        Specification<Property> specification = new PropertySpecification(searchCriteria);
//        return propertyRepository.findAll(specification);
//    }

    public List<Property> searchProperties(SearchCriteria searchCriteria) {
        return propertyRepository.searchProperties(
                searchCriteria.getLocation(),
                searchCriteria.getMinPrice(),
                searchCriteria.getMaxPrice(),
                searchCriteria.getMinSize(),
                searchCriteria.getMaxSize(),
                searchCriteria.getListing(),
                searchCriteria.getMinRooms(),
                searchCriteria.getMaxRooms(),
                searchCriteria.getStatus(),
                searchCriteria.getExcludeLocation()
        );
    }

    public List<Property> sortProperties(String sortBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        return propertyRepository.findAll(sort);
    }
}
