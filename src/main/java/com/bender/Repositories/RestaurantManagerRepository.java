package com.bender.Repositories;

import com.bender.Beans.RestaurantManager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kuka on 2/14/2017.
 */
public interface RestaurantManagerRepository extends JpaRepository<RestaurantManager, Long> {
}
