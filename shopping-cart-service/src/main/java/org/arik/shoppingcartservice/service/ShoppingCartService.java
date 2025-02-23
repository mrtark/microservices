package org.arik.shoppingcartservice.service;

import org.arik.shoppingcartservice.model.Product;
import org.arik.shoppingcartservice.model.ShoppingCart;
import org.arik.shoppingcartservice.repository.ProductRepository;
import org.arik.shoppingcartservice.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ProductRepository productRepository;

    RestTemplate restTemplate;


    public ResponseEntity<ShoppingCart> create(String name) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setShoppincartName(name);
        return ResponseEntity.ok().body(shoppingCartRepository.save(shoppingCart));
    }

    public ResponseEntity<ShoppingCart> addProducts(Long shoppingCartId, List<Product> products) {
        ShoppingCart shoppingCart =
                shoppingCartRepository
                        .findById(shoppingCartId)
                        .orElseThrow(() -> new RuntimeException("Verilen id ile eşleşen bir sonuç bulunamadı"));

        products.forEach(product -> productRepository.saveAndFlush(product));

        Set<Product> newProducts = new HashSet<>(products);
        shoppingCart.setProducts(newProducts);
        return ResponseEntity.ok().body(shoppingCartRepository.save(shoppingCart));
    }


    public ResponseEntity<Map<String, String>> getShoppingCartPrice(Long shoppingCartId) {
        Map<String, String> response = new HashMap<>();

        ShoppingCart shoppingCart =
                shoppingCartRepository
                        .findById(shoppingCartId)
                        .orElseThrow(() -> new RuntimeException("Verilen id ile eşleşen bir sonuç bulunamadı"));

        double totalPrice =
                shoppingCart.getProducts().stream()
                        .map(
                                product ->
                                        restTemplate.getForObject(
                                                "http://PRODUCT-SERVICE/product/" + product.getId(), HashMap.class))
                        .mapToDouble(productResponse -> (double) productResponse.get("price"))
                        .sum();

        // ["Total Price":33];
        response.put("Total Price", Double.toString(totalPrice));
        return ResponseEntity.ok().body(response);
    }

    public ShoppingCart getShoppingCartFindById(Long shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId).orElseThrow(() -> new RuntimeException("Verilen id ile eşleşen bir sonuç bulunamadı"));
    }
}
