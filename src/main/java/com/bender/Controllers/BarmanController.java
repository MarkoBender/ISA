package com.bender.Controllers;

import com.bender.Beans.Barman;
import com.bender.Repositories.BarmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/findOne/{id}")
    public Barman getById(@PathVariable long id){
        return repository.findOne(id);
    }

    @RequestMapping(value = "/changepassword/{id}", method = RequestMethod.PUT)
    public void changepassword(@PathVariable long id, @RequestBody String newPassword) {
        Barman steward = repository.findOne(id);
        steward.setPassword(newPassword);
        steward.setFirstLog(false);
        repository.save(steward);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Barman updatedSteward) {
        Barman steward = updatedSteward;
        steward.setUser_id(id);
        repository.save(steward);
    }
}
