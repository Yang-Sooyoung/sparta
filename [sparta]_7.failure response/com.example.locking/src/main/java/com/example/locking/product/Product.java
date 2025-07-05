package com.example.locking.product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Double price;

	@Version
	private Integer version;  // 버전 필드를 통해 낙관적 락을 구현
}
