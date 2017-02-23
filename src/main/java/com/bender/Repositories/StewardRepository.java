package com.bender.Repositories;

import com.bender.Beans.Steward;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nikola on 16-02-17.
 */
public interface StewardRepository extends JpaRepository<Steward,Long> {
}
