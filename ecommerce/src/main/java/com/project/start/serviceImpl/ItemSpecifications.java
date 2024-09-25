package com.project.start.serviceImpl;


import org.springframework.data.jpa.domain.Specification;

import com.project.start.model.items;

public class ItemSpecifications {

	public static Specification<items> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction(); 
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }
	
	
    public static Specification<items> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return criteriaBuilder.conjunction(); 
            }
            return criteriaBuilder.isMember(categoryId, root.get("categories"));
        };
    }
    
	
	
	public static Specification<items> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return criteriaBuilder.conjunction(); 
            }
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            }
            if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }


	
	
}
