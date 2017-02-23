package com.bender.Repositories;

import com.bender.Beans.Cook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nikola on 16-02-17.
 */
public interface CookRepository extends JpaRepository<Cook,Long> {
}
