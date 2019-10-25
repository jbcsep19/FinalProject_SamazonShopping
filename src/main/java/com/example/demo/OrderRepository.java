package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByOrderStatus(boolean orderStatus);
}
