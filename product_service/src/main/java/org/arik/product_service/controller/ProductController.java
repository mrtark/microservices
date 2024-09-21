package org.arik.product_service.controller;

import org.arik.product_service.dto.ProductRequest;
import org.arik.product_service.dto.ProductResponse;
import org.arik.product_service.model.Product;
import org.arik.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }
    @CrossOrigin
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
