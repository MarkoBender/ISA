package com.bender.Controllers;

import com.bender.Beans.Steward;
import com.bender.Repositories.StewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/findOne/{id}")
    public Steward getById(@PathVariable long id){
        return repository.findOne(id);
    }

    @RequestMapping(value = "/changepassword/{id}", method = RequestMethod.PUT)
    public void changepassword(@PathVariable long id, @RequestBody String newPassword) {
        Steward steward = repository.findOne(id);
        steward.setPassword(newPassword);
        steward.setFirstLog(false);
        repository.save(steward);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Steward updatedSteward) {
        Steward steward = updatedSteward;
        steward.setUser_id(id);
        repository.save(steward);
    }
}
