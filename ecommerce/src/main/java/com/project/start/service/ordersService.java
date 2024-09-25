package com.project.start.service;

import java.util.List;

import com.project.start.model.Orders;
import com.project.start.model.OrderItem;

public interface ordersService {

	public Orders createOrder(List<OrderItem> orderItems);
	public List<Orders> getAllOrders();
	public Orders getbyid(long id);
	public Orders updateOrder(Long id, List<OrderItem> orderItems); // id in order
	public void deleteOrder(Long id);
	
}
