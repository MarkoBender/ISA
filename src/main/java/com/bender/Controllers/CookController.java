package com.bender.Controllers;

import com.bender.Beans.Cook;
import com.bender.Repositories.CookRepository;
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
@RequestMapping(value="/cooks")
public class CookController {

    private CookRepository repository;

    @Autowired
    public CookController(CookRepository repository){
        this.repository=repository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Cook> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Cook newUser){
        System.out.print("hi");
        newUser.setUloga("Cook");
        newUser.setFirstLog(true);
        repository.save(newUser);
    }
}
