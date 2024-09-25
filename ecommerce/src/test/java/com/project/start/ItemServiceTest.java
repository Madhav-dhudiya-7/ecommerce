package com.project.start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.project.start.model.items;
import com.project.start.serviceImpl.itemsServiceImpl;

public class ItemServiceTest {

	  @InjectMocks
	    private itemsServiceImpl itemService;

	    @Mock
	    private com.project.start.repo.itemRepo itemRepo;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetAllItems() throws Exception {
	        when(itemRepo.findAll()).thenReturn(Arrays.asList(new items(), new items()));
	        
	        List<items> items = itemService.getAllItems();
	        
	        assertEquals(2, items.size());
	        verify(itemRepo, times(1)).findAll();
	    }

	    @Test
	    public void testCreateItem() throws Exception {
	    	items item = new items();
	        when(itemRepo.save(item)).thenReturn(item);
	        
	        items createdItem = itemService.creaItems(item);
	        
	        assertNotNull(createdItem);
	        verify(itemRepo, times(1)).save(item);
	    }

	    @Test
	    public void testGetItemById() throws Exception {
	    	items item = new items();
	        when(itemRepo.findById(1L)).thenReturn(Optional.of(item));
	        
	        items foundItem = itemService.getbyId(1L);
	        
	        assertNotNull(foundItem);
	        verify(itemRepo, times(1)).findById(1L);
	    }

	    @Test
	    public void testUpdateItem() throws Exception {
	    	items existingItem = new items();
	        existingItem.setId(1L);
	        when(itemRepo.findById(1L)).thenReturn(Optional.of(existingItem));
	        when(itemRepo.save(existingItem)).thenReturn(existingItem);
	        
	        items updatedItem = new items();
	        updatedItem.setName("New Name");
	        existingItem.setName(updatedItem.getName());
	        
	        items result = itemService.upItems(1L, updatedItem);
	        
	        assertEquals("New Name", result.getName());
	        verify(itemRepo, times(1)).save(existingItem);
	    }

	    @Test
	    public void testDeleteItem() throws Exception {
	        doNothing().when(itemRepo).deleteById(1L);
	        
	        itemService.deletitems(1L);
	        
	        verify(itemRepo, times(1)).deleteById(1L);
	    }
	
}
