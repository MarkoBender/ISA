package com.bender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bender on 12/18/2016.
 */
@RestController
@RequestMapping(value = "/bookings")
public class RestoranController {

    RestoranRepository repository;
    //novi sloj servis ovradjujem dobijeno iz baye
    @Autowired
    public RestoranController(RestoranRepository repository){
        this.repository = repository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Restoran> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/blizu/{distance}" , method = RequestMethod.GET)
    public List<Restoran> getBlizu(@PathVariable int distance){
        return repository.findByDistanceLessThan(distance);
    }

    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public List<Restoran> create(@RequestBody Restoran restoran){
        repository.save(restoran);
        return repository.findAll();
    }

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.POST)
    public List<Restoran> remove(@PathVariable long id){
        repository.delete(id);
        return repository.findAll();
    }

}
