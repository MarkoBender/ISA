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
        /*DishType dt1=new DishType();
        dt1.setName("meso");

        DishType dt2=new DishType();
        dt2.setName("salata");

        DishType dt3=new DishType();
        dt3.setName("supa");

        this.repository.save(dt1);
        this.repository.save(dt2);
        this.repository.save(dt3);*/
    }

    @RequestMapping(value = "/all")
    public List<DishType> getAll(){
        return repository.findAll();
    }
}
