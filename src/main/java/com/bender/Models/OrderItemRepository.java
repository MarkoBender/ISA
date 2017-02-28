package com.bender.Models;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 2/28/2017.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
