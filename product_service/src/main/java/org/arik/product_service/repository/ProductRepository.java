package org.arik.product_service.repository;

import org.arik.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Long> {
}
