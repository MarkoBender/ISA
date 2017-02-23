package com.bender.Repositories;

import com.bender.Beans.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nikola on 14-02-17.
 */
public interface DrinkTypeRepository extends JpaRepository<DrinkType,Long> {
}
