package com.bender.Controllers;

import com.bender.Beans.DishType;
import com.bender.Repositories.DishTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nikola on 15-02-17.
 */
@RestController
@RequestMapping(value ="/dishtypes")
public class DishTypeController {

    private DishTypeRepository repository;

    @Autowired
    public DishTypeController(DishTypeRepository repository){

        this.repository = repository;
    }

    @RequestMapping(value = "/all")
    public List<DishType> getAll(){
        return repository.findAll();
    }
}
