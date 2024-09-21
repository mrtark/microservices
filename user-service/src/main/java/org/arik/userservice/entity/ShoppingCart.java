package org.arik.userservice.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

public class ShoppingCart {
    @Id
    private long id;
    private String shoppingCartName;

    @ManyToMany(mappedBy = "products")
    private Set<ShoppingCart> shoppingCarts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShoppingCartName() {
        return shoppingCartName;
    }

    public void setShoppingCartName(String shoppingCartName) {
        this.shoppingCartName = shoppingCartName;
    }

}
