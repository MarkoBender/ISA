package com.bender.Repositories;

import com.bender.Beans.Reservation;
import com.bender.Beans.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bender on 1/5/2017.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
