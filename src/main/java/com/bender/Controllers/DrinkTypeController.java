package com.bender.Controllers;


import com.bender.Beans.DrinkType;
import com.bender.Repositories.DrinkTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nikola on 18-02-17.
 */
@RestController
@RequestMapping(value="/drinktypes")
public class DrinkTypeController {

    private DrinkTypeRepository repository ;

    @Autowired
    public DrinkTypeController (DrinkTypeRepository repository) {
        this.repository = repository;
}

    @RequestMapping(value = "/all")
    public List<DrinkType> getAll(){
        return repository.findAll();
    }
}
