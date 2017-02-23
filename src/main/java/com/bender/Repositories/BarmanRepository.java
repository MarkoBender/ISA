package com.bender.Repositories;

import com.bender.Beans.Barman;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nikola on 16-02-17.
 */
public interface BarmanRepository extends JpaRepository<Barman,Long> {
}
