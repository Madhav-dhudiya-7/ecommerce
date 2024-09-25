package com.project.start.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.start.model.OrderItem;
import com.project.start.model.Orders;
import com.project.start.model.items;
import com.project.start.repo.OrderRepo;
import com.project.start.service.ordersService;

@Service
public class OrderServiceImpl implements ordersService {

	 @Autowired
	    private OrderRepo orderRepo;

	    @Autowired
	    private com.project.start.repo.itemRepo itemRepo;

	    public Orders createOrder(List<OrderItem> orderItems) {
	        Orders order = new Orders();
	        order.setOrderDate(LocalDateTime.now());

	        // Sanitize order items before saving
	        sanitizeOrderItems(orderItems);
	        
	        List<OrderItem> savedOrderItems = new ArrayList<>();
	        double totalPrice = 0;

	        for (OrderItem orderItem : orderItems) {
	            items item = itemRepo.findById(orderItem.getItem().getId())
	                .orElseThrow(() -> new RuntimeException("Item not found"));
	            orderItem.setOrder(order);
	            orderItem.setPrice(orderItem.getQuantity() * item.getPrice());
	            savedOrderItems.add(orderItem);
	            totalPrice += orderItem.getPrice();
	        }

	        order.setOrderItems(savedOrderItems);
	        order.setTotalPrice(totalPrice);
	        return orderRepo.save(order);
	    }

	    public List<Orders> getAllOrders() {
	        return orderRepo.findAll();
	    }

	    public Orders getbyid(long id) {
	        return orderRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found"));
	    }

	    public Orders updateOrder(Long id, List<OrderItem> orderItems) {
	        Orders order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

	        // Clear existing order items
	        order.getOrderItems().clear();

	        // Sanitize new order items
	        sanitizeOrderItems(orderItems);

	        List<OrderItem> updatedOrderItems = new ArrayList<>();
	        double totalPrice = 0;

	        for (OrderItem orderItem : orderItems) {
	            items item = itemRepo.findById(orderItem.getItem().getId())
	                    .orElseThrow(() -> new RuntimeException("Item not found"));
	            orderItem.setOrder(order);
	            orderItem.setPrice(orderItem.getQuantity() * item.getPrice());
	            updatedOrderItems.add(orderItem);
	            totalPrice += orderItem.getPrice();
	        }

	        order.setOrderItems(updatedOrderItems);
	        order.setTotalPrice(totalPrice);
	        return orderRepo.save(order);
	    }

	    public void deleteOrder(Long id) {
	        orderRepo.deleteById(id);
	    }

	    private void sanitizeOrderItems(List<OrderItem> orderItems) {
	        for (OrderItem orderItem : orderItems) {
	            // Assuming orderItem has a method to sanitize its fields
	            if (orderItem.getItem() != null) {
	                String itemName = orderItem.getItem().getName();
	                orderItem.getItem().setName(Encode.forHtml(itemName)); // Sanitize item name
	                // Sanitize other fields as needed
	            }
	        }
	    }

		

}
