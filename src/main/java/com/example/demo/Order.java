package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity

@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long OrderId;
    //private double total_cost;

    private boolean orderStatus;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
    private Collection<Product> products;


    public Order() {
    }

    public Order(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    /*   public double getTotal_cost() {
            return total_cost;
        }

        public void setTotal_cost(double total_cost) {
            this.total_cost = total_cost;
        }
    */
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

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        this.OrderId = orderId;
    }


    public String calculateCost(Collection<Product> products) {
        double total = 0.0;
//        ArrayList<Product> products = new ArrayList<>(getProducts());
        for (Product prod : products) {
            double price = Double.parseDouble(prod.getPrice());
            total += price;
        }
        return Double.toString(total);
    }

    public String productQuantity() {
        return Integer.toString(getProducts().size());
    }

    public String calculateShipping(Collection<Product> products) {
        String costString = calculateCost( products);
        double cost = (Double.valueOf(costString)).doubleValue();
        double shippingCost = 0.0;
        if (cost < 50) {
            shippingCost = (cost * .05);
            return Double.toString(shippingCost);
        }
        return Double.toString(shippingCost);
    }

    public String costPlusShipping(Collection<Product> products) {
        String shippingString = calculateShipping(products);
        double shipping = (Double.valueOf(shippingString)).doubleValue();
        String productString = calculateCost(products);
        double productCost = (Double.valueOf(productString)).doubleValue();

        return Double.toString(shipping + productCost);
    }

    public void checkout() {

    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
}

