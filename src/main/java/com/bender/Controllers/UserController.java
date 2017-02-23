package com.bender.Controllers;

import com.bender.Beans.Guest;
import com.bender.Beans.User;
import com.bender.Repositories.GuestRepository;
import com.bender.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bender on 12/25/2016.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<User> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/getOne/{id}")
    public User getById(@PathVariable long id){
        return repository.findOne(id);
    }



    @RequestMapping(value = "/exists" , method = RequestMethod.PUT)
    public User exists(@RequestBody User sentUser){
        User user = repository.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        if(user != null)
            return user;
        else
            return new User();
    }

    @RequestMapping(value = "/existsEmail" , method = RequestMethod.POST)
    public boolean existsEmail(@RequestBody String email){
        User user = repository.findByEmail(email);
        if(user == null)
            return false;
        else
            return true;
    }


    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody User newUser){
        repository.save(newUser);
    }


}
