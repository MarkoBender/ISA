package com.bender.Repositories;

import com.bender.Beans.Restaurant;
import com.bender.Beans.RestaurantRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nikola on 26-02-17.
 */
public interface RestaurantRegionRepository extends JpaRepository<RestaurantRegion,Long> {

    List<RestaurantRegion> findByRestaurant(Restaurant restaurant);
}
