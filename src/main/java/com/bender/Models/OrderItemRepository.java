package com.bender.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/28/2017.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
