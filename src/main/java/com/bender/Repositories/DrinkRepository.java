package com.bender.Repositories;

import com.bender.Beans.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nikola on 14-02-17.
 */
public interface DrinkRepository extends JpaRepository<Drink,Long> {
}
