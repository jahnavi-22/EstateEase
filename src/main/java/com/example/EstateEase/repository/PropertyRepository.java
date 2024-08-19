package com.example.EstateEase.repository;

import com.example.EstateEase.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;


import java.util.*;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {
    List<Property> findByUserId(Long userId);
    List<Property> findAll(Sort sort);


    @Query("SELECT p FROM Property p WHERE " +
            "(:location IS NULL AND :excludeLocation IS NOT NULL AND p.location <> :excludeLocation OR " +
            ":location IS NOT NULL AND :excludeLocation IS NULL AND p.location = :location OR " +
            ":location IS NULL AND :excludeLocation IS NULL) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:minSize IS NULL OR p.size >= :minSize) AND " +
            "(:maxSize IS NULL OR p.size <= :maxSize) AND " +
            "(:listing IS NULL OR p.listing = :listing) AND " +
            "(:minRooms IS NULL OR p.rooms >= :minRooms) AND " +
            "(:maxRooms IS NULL OR p.rooms <= :maxRooms) AND " +
            "(:status IS NULL OR p.status = :status)")
    List<Property> searchProperties(
            @Param("location") String location,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minSize") Double minSize,
            @Param("maxSize") Double maxSize,
            @Param("listing") String listing,
            @Param("minRooms") Integer minRooms,
            @Param("maxRooms") Integer maxRooms,
            @Param("status") String status,
            @Param("excludeLocation") String excludeLocation
    );
}

