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
        /*
        DrinkType dt1=new DrinkType();
        dt1.setName("bezalkoholni");

        DrinkType dt2=new DrinkType();
        dt2.setName("alkohol");

        this.repository.save(dt1);
        this.repository.save(dt2);*/
}

    @RequestMapping(value = "/all")
    public List<DrinkType> getAll(){
        return repository.findAll();
    }
}
