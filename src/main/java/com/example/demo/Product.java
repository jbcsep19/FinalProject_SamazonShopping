package com.example.demo;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    private String name;
    private String description;
    private String price;
    private String imageURL;
    private boolean active;
//    private int quantity;
    @ManyToMany(mappedBy = "products")
    private Collection<Order> orders;

    public Product(){}

    public Product( String name, String description, String price, String image, boolean active){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageURL = image;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

/*   public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        Product.quantity = quantity;
    }
*/
    public long getProductId() { return productId; }

    public void setProductId(long productId) { this.productId = productId; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }
}
