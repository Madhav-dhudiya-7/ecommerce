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
import org.springframework.web.bind.annotation.RestController;

import com.project.start.model.OrderItem;
import com.project.start.model.Orders;
import com.project.start.service.ordersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Orders", description = "Operations for managing orders")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private ordersService ordersService;

    @Operation(summary = "Create a new order", description = "Create a new order by adding items to the cart")
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody List<OrderItem> orderItems) {
        Orders order = ordersService.createOrder(orderItems);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all orders", description = "Retrieve all orders made by users")
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "Get order by ID", description = "Retrieve the details of a specific order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@Parameter(description = "ID of the order to retrieve") @PathVariable Long id) {
        Orders order = ordersService.getbyid(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update an order", description = "Update the status or items of an existing order")
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateOrder(@Parameter(description = "ID of the order to update") @PathVariable Long id, @RequestBody List<OrderItem> orderItems) {
        ordersService.updateOrder(id, orderItems);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete an order", description = "Cancel or delete an order by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@Parameter(description = "ID of the order to delete") @PathVariable Long id) {
        ordersService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
