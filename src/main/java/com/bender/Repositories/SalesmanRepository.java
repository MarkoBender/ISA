package com.bender.Repositories;

import com.bender.Beans.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nikola on 14-02-17.
 */
public interface SalesmanRepository extends JpaRepository<Salesman,Long> {
}
