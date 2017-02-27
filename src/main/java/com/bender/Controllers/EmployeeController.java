package com.bender.Controllers;

import com.bender.Beans.Employee;
import com.bender.Beans.Restaurant;
import com.bender.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by User on 2/26/2017.
 */
@RestController
@RequestMapping(value ="/employees")
public class EmployeeController {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeController (EmployeeRepository repository){this.repository=repository;}

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Employee> getAll(){
        return repository.findAll();
    }
    @RequestMapping(value = "/forRestaurant", method=RequestMethod.POST)
    public List<Employee> getforRestaurant(@RequestBody Restaurant restaurant) {
        return repository.findByRestaurant(restaurant);
    }

}
