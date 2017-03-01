package com.bender.Repositories;

import com.bender.Beans.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 3/1/2017.
 */
public interface BillRepository extends JpaRepository<Bill,Long> {
}
