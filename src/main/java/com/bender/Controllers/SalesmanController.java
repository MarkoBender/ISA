package com.bender.Controllers;

import com.bender.Beans.Offer;
import com.bender.Beans.Salesman;
import com.bender.Repositories.SalesmanRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikola on 17-02-17.
 */
@RestController
@RequestMapping(value="/salesmen")
public class SalesmanController {

    private SalesmanRepository repository;

    public SalesmanController(SalesmanRepository repository){this.repository = repository;}

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Salesman> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Salesman newUser){
        newUser.setUloga("Salesman");
        newUser.setFirstLog(true);
        repository.save(newUser);
    }

    @RequestMapping(value = "/findOne/{id}")
    public Salesman getById(@PathVariable long id){
        return repository.findOne(id);
    }

    @RequestMapping(value = "/changepassword/{id}", method = RequestMethod.PUT)
    public void changepassword(@PathVariable long id, @RequestBody String newPassword) {
        Salesman salesman = repository.findOne(id);
        salesman.setPassword(newPassword);
        salesman.setFirstLog(false);
        repository.save(salesman);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Salesman updatedSalesman) {
        Salesman salesman = updatedSalesman;
        salesman.setUser_id(id);
        repository.save(salesman);
    }
}
