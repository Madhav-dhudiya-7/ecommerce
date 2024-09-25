package com.project.start.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.start.model.items;
import com.project.start.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Items", description = "CRUD operations for managing items")
@RestController
@RequestMapping("/api/items")
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Get all items", description = "Retrieve a list of all items")
    @GetMapping
    public ResponseEntity<List<items>> getAllItems() throws Exception {
        List<items> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Operation(summary = "Create a new item", description = "Add a new item to the catalog")
    @PostMapping
    public ResponseEntity<items> createItem(@RequestBody items item) throws Exception {
        items createdItem = itemService.creaItems(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @Operation(summary = "Get item by ID", description = "Retrieve a specific item by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<items> getItemById(@Parameter(description = "ID of the item to retrieve") @PathVariable Long id) throws Exception {
        items item = itemService.getbyId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Operation(summary = "Update an item", description = "Update the details of an existing item")
    @PutMapping("/{id}")
    public ResponseEntity<items> updateItem(@Parameter(description = "ID of the item to update") @PathVariable Long id, @RequestBody items item) throws Exception {
        items updatedItem = itemService.upItems(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @Operation(summary = "Delete an item", description = "Delete an item by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@Parameter(description = "ID of the item to delete") @PathVariable Long id) throws Exception {
        itemService.deletitems(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Search items", description = "Search for items by name, category, and price range")
    @GetMapping("/search")
    public ResponseEntity<List<items>> searchItems(
            @Parameter(description = "Name of the item") @RequestParam(required = false) String name,
            @Parameter(description = "Category of the item") @RequestParam(required = false) String categoryName,
            @Parameter(description = "Minimum price") @RequestParam(required = false) Double minPrice,
            @Parameter(description = "Maximum price") @RequestParam(required = false) Double maxPrice) throws Exception {
        List<items> results = itemService.searchItems(name, categoryName, minPrice, maxPrice);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
