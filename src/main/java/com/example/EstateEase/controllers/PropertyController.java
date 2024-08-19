package com.example.EstateEase.controllers;

import com.example.EstateEase.model.Property;
import com.example.EstateEase.model.SearchCriteria;
import com.example.EstateEase.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/list")
    public ResponseEntity<Map<String, Object>> listProperty(@RequestBody Property property){
        Property listedProperty = propertyService.listProperty(property);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Property listed successfully");
        response.put("data", listedProperty);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getProperties(@PathVariable Long userId){
        List<Property> properties = propertyService.getPropertiesByUserId(userId);
        Map<String, Object> response = new HashMap<>();
        if(properties.isEmpty()){
            response.put("message", "No properties found.");
            response.put("data", properties);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        response.put("message", "Properties retrieved successfully.");
        response.put("data", properties);
        return ResponseEntity.ok(response);
    }




    @PatchMapping("/{id}/mark-sold")
    public ResponseEntity<Map<String, Object>> markPropertyAsSold(@PathVariable Long id) {
        Property property = propertyService.findById(id).orElse(null);
        if (property == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Property not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        property.setStatus("SOLD");
        propertyService.listProperty(property);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Property marked as sold.");
        response.put("data", property);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProperty(@RequestBody SearchCriteria searchCriteria) {
        List<Property> properties = propertyService.searchProperties(searchCriteria);
        Map<String, Object> response = new HashMap<>();
        if (properties.isEmpty()) {
            response.put("message", "No properties found.");
            response.put("data", properties);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        response.put("message", "Properties retrieved successfully.");
        response.put("data", properties);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sort")
    public ResponseEntity<Map<String, Object>> sortProperties(@RequestParam String sortBy) {
        List<Property> properties = propertyService.sortProperties(sortBy);
        Map<String, Object> response = new HashMap<>();
        if (properties.isEmpty()) {
            response.put("message", "No properties found.");
            response.put("data", properties);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        response.put("message", "Properties retrieved successfully.");
        response.put("data", properties);
        return ResponseEntity.ok(response);
    }


}
