package com.college.lostandfoundapi.controller;

import com.college.lostandfoundapi.model.Item;
import com.college.lostandfoundapi.model.ItemStatus;
import com.college.lostandfoundapi.security.Role;
import com.college.lostandfoundapi.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Public endpoint for reading items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.findAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Public endpoint for reading found items
    @GetMapping("/found")
    public ResponseEntity<List<Item>> getFoundItems() {
        List<Item> foundItems = itemService.findFoundItems();
        return new ResponseEntity<>(foundItems, HttpStatus.OK);
    }

    // Public endpoint for reading a single item
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.findItemById(id);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Protected endpoint for creating items (USER and ADMIN)
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item, HttpServletRequest request) {
        Role userRole = (Role) request.getAttribute("userRole");
        if (userRole == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Item newItem = itemService.saveItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    // Protected endpoint for creating lost items (USER and ADMIN)
    @PostMapping("/lost")
    public ResponseEntity<Item> createLostItem(@RequestBody Item item, HttpServletRequest request) {
        Role userRole = (Role) request.getAttribute("userRole");
        if (userRole == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        item.setStatus(ItemStatus.LOST);
        Item newItem = itemService.saveItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    // Protected endpoint for updating an item (ADMIN only)
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem, HttpServletRequest request) {
        Role userRole = (Role) request.getAttribute("userRole");
        if (userRole == null || userRole != Role.ROLE_ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Optional<Item> item = itemService.updateItem(id, updatedItem);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Protected endpoint for deleting an item (ADMIN only)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id, HttpServletRequest request) {
        Role userRole = (Role) request.getAttribute("userRole");
        if (userRole == null || userRole != Role.ROLE_ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        boolean isDeleted = itemService.deleteItem(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}