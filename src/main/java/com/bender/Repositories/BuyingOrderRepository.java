package com.bender.Repositories;

import com.bender.Beans.BuyingOrder;
import com.bender.Beans.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nikola on 14-02-17.
 */
public interface BuyingOrderRepository extends JpaRepository<BuyingOrder,Long> {

    List<BuyingOrder> findByRestaurant (Restaurant restaurant);
}
