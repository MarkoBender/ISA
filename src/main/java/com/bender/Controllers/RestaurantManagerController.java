package com.bender.Controllers;

import com.bender.Beans.Restaurant;
import com.bender.Beans.RestaurantManager;
import com.bender.Repositories.RestaurantManagerRepository;
import com.bender.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kuka on 2/14/2017.
 */
@RestController
@RequestMapping(value = "/restaurantManagers")
public class RestaurantManagerController {

    private RestaurantManagerRepository repository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantManagerController(RestaurantManagerRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @RequestMapping(value = "/all")
    public List<RestaurantManager> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody RestaurantManager newRestaurantManager){
        newRestaurantManager.setUloga("RestaurantManager");
        repository.save(newRestaurantManager);
    }

    @RequestMapping(value = "/findOne/{id}")
    public RestaurantManager getRestaurantManager(@PathVariable long id) {
        RestaurantManager restaurantManager = repository.findOne(id);
        return restaurantManager;
    }

    @RequestMapping(value = "/availableRestaurants")
    public List<Restaurant> getAvailableRestaurants(){
        List<Restaurant> restaurants=restaurantRepository.findAll();
        List<RestaurantManager> restaurantManagers=repository.findAll();

        for(RestaurantManager rs : restaurantManagers){
            restaurants.remove(rs.getRestaurant());
        }

        System.out.println(restaurants.size());
        return restaurants;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody RestaurantManager updatedRestaurantManager) {
        RestaurantManager restaurantManager=updatedRestaurantManager;
        repository.save(restaurantManager);
    }

    @RequestMapping(value = "/availableRestaurantsForUpdate/{id}")
    public List<Restaurant> getAvailableRestaurantsForUpdate(@PathVariable long id){
        List<Restaurant> restaurants=restaurantRepository.findAll();
        List<RestaurantManager> restaurantManagers=repository.findAll();

        RestaurantManager rm=repository.findOne(id);

        for(RestaurantManager rs : restaurantManagers){
            restaurants.remove(rs.getRestaurant());
        }

        if(rm.getRestaurant()!=null)
            restaurants.add(rm.getRestaurant());



        System.out.println(restaurants.size());
        return restaurants;
    }
}
