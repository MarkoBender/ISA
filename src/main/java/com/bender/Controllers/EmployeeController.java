package com.bender.Controllers;

import com.bender.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 2/26/2017.
 */
@RestController
@RequestMapping(value ="/employees")
public class EmployeeController {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeController (EmployeeRepository repository){this.repository=repository;}
}
