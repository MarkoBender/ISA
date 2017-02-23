package com.bender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bender on 12/18/2016.
 */
@Repository
public interface RestoranRepository extends JpaRepository<Restoran, Long>{

    List<Restoran> findByDistanceLessThan(int distance);
}
