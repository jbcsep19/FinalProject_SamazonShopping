package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private double total_cost;

    @ManyToOne
    @JoinTable(joinColumns = @JoinColumn (name="order_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private User user;

    @ManyToMany
    private Collection<Product> products;

    public Order(){}

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public long getOrderId() { return orderId; }

    public void setOrderId(long orderId) { this.orderId = orderId; }

    public double calculateCost(){
        double total = 0;
        ArrayList<Product> products = new ArrayList<>(getProducts());
        for (Product prod : products){
            double price = Double.parseDouble(prod.getPrice());
            total += price;
        }
        return total;
    }

    public int productQuantity(){

        return getProducts().size();

    }

    public double calculateShipping(){
        double cost = calculateCost();
        double shippingCost = 0;
        if(cost < 50){
            shippingCost =  (cost * .05);
            return shippingCost;
        }
        return shippingCost;
    }


}
