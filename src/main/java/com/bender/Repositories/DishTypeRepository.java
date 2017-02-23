package com.bender.Repositories;

import com.bender.Beans.DishType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nikola on 14-02-17.
 */
public interface DishTypeRepository extends JpaRepository<DishType,Long> {
}
