package org.arik.product_service.service;

import lombok.extern.slf4j.Slf4j;
import org.arik.product_service.dto.ProductRequest;
import org.arik.product_service.dto.ProductResponse;
import org.arik.product_service.model.Product;
import org.arik.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product Created Successfully");
        return product;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(ProductResponse::new).toList();
    }


    public ProductResponse findProductById(Long id) {
        return productRepository.findById(id).map(ProductResponse::new).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
