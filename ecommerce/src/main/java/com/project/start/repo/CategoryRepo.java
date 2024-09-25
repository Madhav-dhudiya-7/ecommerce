package com.project.start.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.start.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

	  Category findByName(String name); 
}
