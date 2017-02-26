package com.bender.Repositories;

import com.bender.Beans.FoodDrinkItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 2/26/2017.
 */
public interface FoodDrinkItemRepository extends JpaRepository<FoodDrinkItem, Long> {
}
