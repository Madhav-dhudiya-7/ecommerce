package com.project.start.model;

import java.util.List;

import org.owasp.encoder.Encode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class items {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private Double price;
	
	@ManyToMany
	private List<Category> categories;
	
	 public void sanitize() {
	        this.name = Encode.forHtml(this.name);
	        this.description = Encode.forHtml(this.description);
	     
	    }
	
}
