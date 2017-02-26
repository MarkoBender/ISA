package com.bender.Controllers;

import com.bender.Beans.Cook;
import com.bender.Repositories.CookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/findOne/{id}")
    public Cook getById(@PathVariable long id){
        return repository.findOne(id);
    }

    @RequestMapping(value = "/changepassword/{id}", method = RequestMethod.PUT)
    public void changepassword(@PathVariable long id, @RequestBody String newPassword) {
        Cook steward = repository.findOne(id);
        steward.setPassword(newPassword);
        steward.setFirstLog(false);
        repository.save(steward);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Cook updatedSteward) {
        Cook steward = updatedSteward;
        steward.setUser_id(id);
        repository.save(steward);
    }
}
