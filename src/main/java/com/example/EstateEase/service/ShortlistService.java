package com.example.EstateEase.service;

import com.example.EstateEase.model.Shortlist;
import com.example.EstateEase.repository.ShortlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortlistService {

    @Autowired
    private ShortlistRepository shortlistRepository;

    public Shortlist shortlist(Shortlist shortlist) {
        return shortlistRepository.save(shortlist);
    }

    public List<Shortlist> shortlistByUserId(Long userId) {
        return shortlistRepository.findByUserId(userId);
    }
}
