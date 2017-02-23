package com.bender.Controllers;

import com.bender.Beans.Steward;
import com.bender.Repositories.StewardRepository;
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
@RequestMapping(value="/stewards")
public class StewardController {

    private StewardRepository repository;

    @Autowired
    public StewardController(StewardRepository repository){
        this.repository=repository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Steward> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Steward newUser){
        newUser.setUloga("Steward");
        newUser.setFirstLog(true);
        repository.save(newUser);
    }
}
