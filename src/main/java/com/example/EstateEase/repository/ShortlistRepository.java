package com.example.EstateEase.repository;

import com.example.EstateEase.model.Shortlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortlistRepository extends JpaRepository<Shortlist, Long> {
    List<Shortlist> findByUserId(Long userId);
}
