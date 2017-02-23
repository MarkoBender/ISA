package com.bender.Controllers;

import com.bender.Beans.Guest;
import com.bender.Beans.Reservation;
import com.bender.Beans.Restaurant;
import com.bender.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bender on 1/5/2017.
 */

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private RestaurantRepository repository;

    @Autowired
    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/all")
    public List<Restaurant> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Restaurant newRestaurant){
        repository.save(newRestaurant);
    }

    @RequestMapping(value = "/findOne/{id}")
    public Restaurant getRestaurant(@PathVariable long id) {
        Restaurant restaurant = repository.findOne(id);
        return restaurant;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurant=updatedRestaurant;
        restaurant.setRestaurant_id(id);
        repository.save(restaurant);
    }


}
