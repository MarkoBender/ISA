package com.bender.Repositories;

import com.bender.Beans.FoodDrinkItem;
import com.bender.Beans.OrderItem;
import com.bender.Beans.Steward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by User on 2/28/2017.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem> findByFoodDrinkItem (FoodDrinkItem foodDrinkItem);
    List<OrderItem> findBySteward (Steward steward);

}
