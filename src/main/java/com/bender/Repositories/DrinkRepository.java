package com.bender.Repositories;

import com.bender.Beans.Drink;
import com.bender.Beans.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nikola on 14-02-17.
 */
public interface DrinkRepository extends JpaRepository<Drink,Long> {

    List<Drink> findByRestaurant(Restaurant restaurant);
}
