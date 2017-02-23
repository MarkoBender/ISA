package com.bender.Controllers;

import com.bender.Beans.Barman;
import com.bender.Repositories.BarmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nikola on 16-02-17.
 */
@RestController
@RequestMapping(value ="/barmen")
public class BarmanController {

    private BarmanRepository repository;

    @Autowired
    public BarmanController (BarmanRepository repository){this.repository=repository;}

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Barman> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Barman newUser){
        System.out.print("hi");
        newUser.setUloga("Barman");
        newUser.setFirstLog(true);
        repository.save(newUser);
    }
}
