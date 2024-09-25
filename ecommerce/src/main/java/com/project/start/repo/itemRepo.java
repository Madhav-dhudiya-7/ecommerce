package com.project.start.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.start.model.items;

public interface itemRepo extends JpaRepository<items, Long>, JpaSpecificationExecutor<items> {

}
