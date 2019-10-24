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

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="order_id"))
    private Collection<Order> orders;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Product(){}

    public Product(String name, String description, String price, String image, boolean active){
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", active=" + active +
                '}';
    }
}
