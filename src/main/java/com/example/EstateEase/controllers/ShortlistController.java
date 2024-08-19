package com.example.EstateEase.controllers;

import com.example.EstateEase.model.Property;
import com.example.EstateEase.model.Shortlist;
import com.example.EstateEase.service.PropertyService;
import com.example.EstateEase.service.ShortlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shortlists")
public class ShortlistController {

    @Autowired
    private ShortlistService shortlistService;

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/shortlist")
    public ResponseEntity<Map<String, Object>> shortlistProperty(@RequestBody Shortlist shortlist) {
        Property property = propertyService.findById(shortlist.getPropertyId()).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if (property == null) {
            response.put("message", "Property not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Shortlist savedShortlist = shortlistService.shortlist(shortlist);
        response.put("message", "Property shortlisted successfully.");
        response.put("data", savedShortlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getShortlistedProperties(@PathVariable Long userId){
        List<Shortlist> shortlists = shortlistService.shortlistByUserId(userId);
        Map<String, Object> response = new HashMap<>();
        if(shortlists.isEmpty()){
            response.put("message", "No shortlisted properties found.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        response.put("message", "Shortlisted properties retrieved successfully.");
        response.put("data", shortlists);
        return ResponseEntity.ok(response);
    }
}
