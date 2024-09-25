package com.project.start.service;

import java.math.BigDecimal;
import java.util.List;

import com.project.start.model.items;

public interface ItemService {

	public List<items> getAllItems() throws Exception;
	public items creaItems(items items) throws Exception;
	public items getbyId(long id) throws Exception;
	public items upItems (long id , items updateditems) throws Exception;
	public void deletitems(long id) throws Exception;
	public List<items> searchItems(String name, String categoryName, Double minPrice, Double maxPrice);
}
