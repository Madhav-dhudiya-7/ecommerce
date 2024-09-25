package com.project.start.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.start.model.Category;
import com.project.start.model.items;
import com.project.start.repo.CategoryRepo;
import com.project.start.repo.itemRepo;
import com.project.start.service.ItemService;

@Service
public class itemsServiceImpl implements ItemService {

    @Autowired
    private itemRepo itemrepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<items> getAllItems() throws Exception {
        return itemrepo.findAll();
    }

    @Override
    public items creaItems(items item) throws Exception {
        item.sanitize(); // Sanitize before saving
        return itemrepo.save(item);
    }

    @Override
    public items getbyId(long id) throws Exception {
        return itemrepo.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public items upItems(long id, items updatedItem) throws Exception {
        items existingItem = getbyId(id);
        existingItem.setName(updatedItem.getName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setCategories(updatedItem.getCategories());
        existingItem.sanitize(); // Sanitize before saving
        return itemrepo.save(existingItem);
    }

    @Override
    public void deletitems(long id) throws Exception {
        itemrepo.deleteById(id);
    }

    // Search
    @Override
    public List<items> searchItems(String name, String categoryName, Double minPrice, Double maxPrice) {
        Long categoryId = null;

        if (categoryName != null && !categoryName.isEmpty()) {
            // Fetch the category ID by name
            Category category = categoryRepo.findByName(categoryName);
            if (category != null) {
                categoryId = category.getId();
            }
        }

        Specification<items> spec = Specification.where(ItemSpecifications.hasName(name))
                .and(ItemSpecifications.hasCategory(categoryId))
                .and(ItemSpecifications.hasPriceBetween(minPrice, maxPrice));

        return itemrepo.findAll(spec);
    }

}
