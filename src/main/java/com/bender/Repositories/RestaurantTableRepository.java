package com.bender.Repositories;

import com.bender.Beans.Restaurant;
import com.bender.Beans.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nikola on 26-02-17.
 */
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable,Long> {

    List<RestaurantTable> findByRestaurant(Restaurant restaurant);
}
