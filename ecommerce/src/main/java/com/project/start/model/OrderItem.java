package com.project.start.model;

import org.owasp.encoder.Encode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private items item;

    private int quantity;
    private Double price;
	
    
    public void sanitize() {
        if (item != null) {
            // Encode any string fields to prevent XSS
            String sanitizedName = Encode.forHtml(item.getName());
            item.setName(sanitizedName); // Ensure you have a setter in your Item class
        }
    }
    
}
