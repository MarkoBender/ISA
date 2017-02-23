package com.bender.Repositories;

import com.bender.Beans.Dish;
import com.bender.Beans.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nikola on 14-02-17.
 */
public interface DishRepository extends JpaRepository<Dish,Long> {

    List<Dish> findByRestaurant(Restaurant restaurant);

}
