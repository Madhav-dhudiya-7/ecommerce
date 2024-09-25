package com.project.start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.start.model.OrderItem;
import com.project.start.model.Orders;
import com.project.start.model.items;
import com.project.start.repo.OrderRepo;
import com.project.start.serviceImpl.OrderServiceImpl;

public class OrderServiceTest {

	 @InjectMocks
	    private OrderServiceImpl orderService;

	    @Mock
	    private OrderRepo orderRepo;

	    @Mock
	    private com.project.start.repo.itemRepo itemRepo;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testCreateOrder() {
	        OrderItem orderItem = new OrderItem();
	        orderItem.setQuantity(2);
	        orderItem.setItem(new items(1L, "Test Item","description", 100.0, null));

	        when(itemRepo.findById(1L)).thenReturn(Optional.of(orderItem.getItem()));
	        when(orderRepo.save(any(Orders.class))).thenAnswer(i -> i.getArguments()[0]);

	        Orders order = orderService.createOrder(Arrays.asList(orderItem));

	        assertNotNull(order);
	        assertEquals(200.0, order.getTotalPrice());
	        verify(orderRepo, times(1)).save(any(Orders.class));
	    }

	    @Test
	    public void testGetAllOrders() {
	        when(orderRepo.findAll()).thenReturn(Arrays.asList(new Orders(), new Orders()));

	        List<Orders> orders = orderService.getAllOrders();

	        assertEquals(2, orders.size());
	        verify(orderRepo, times(1)).findAll();
	    }

	    @Test
	    public void testGetOrderById() {
	        Orders order = new Orders();
	        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));

	        Orders foundOrder = orderService.getbyid(1L);

	        assertNotNull(foundOrder);
	        verify(orderRepo, times(1)).findById(1L);
	    }

	    @Test
	    public void testUpdateOrder() {
	        Orders existingOrder = new Orders();
	        existingOrder.setId(1L);
	        when(orderRepo.findById(1L)).thenReturn(Optional.of(existingOrder));

	        OrderItem orderItem = new OrderItem();
	        orderItem.setQuantity(2);
	        orderItem.setItem(new items(1L, "Test Item","description", 100.0, null));

	        when(itemRepo.findById(1L)).thenReturn(Optional.of(orderItem.getItem()));

	        Orders updatedOrder = orderService.updateOrder(1L, Arrays.asList(orderItem));

	        assertNotNull(updatedOrder);
	        assertEquals(200.0, updatedOrder.getTotalPrice());
	        verify(orderRepo, times(1)).save(existingOrder);
	    }

	    @Test
	    public void testDeleteOrder() {
	        doNothing().when(orderRepo).deleteById(1L);

	        orderService.deleteOrder(1L);

	        verify(orderRepo, times(1)).deleteById(1L);
	    }
	
}
