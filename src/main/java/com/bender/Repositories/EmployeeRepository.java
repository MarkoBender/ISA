package com.bender.Repositories;

import com.bender.Beans.Employee;
import com.bender.Beans.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by User on 2/26/2017.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByRestaurant(Restaurant restaurant);

}
