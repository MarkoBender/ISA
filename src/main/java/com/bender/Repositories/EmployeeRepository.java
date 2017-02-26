package com.bender.Repositories;

import com.bender.Beans.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 2/26/2017.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
