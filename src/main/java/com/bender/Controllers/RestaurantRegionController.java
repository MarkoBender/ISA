package com.bender.Controllers;

import com.bender.Beans.BuyingOrder;
import com.bender.Beans.Restaurant;
import com.bender.Beans.RestaurantRegion;
import com.bender.Repositories.RestaurantRegionRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nikola on 26-02-17.
 */
@RestController
@RequestMapping(value="/restaurantregions")
public class RestaurantRegionController {

    private RestaurantRegionRepository repository;

    public RestaurantRegionController (RestaurantRegionRepository repository){
        this.repository = repository;
    }

    @RequestMapping(value = "/all")
    public List<RestaurantRegion> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody RestaurantRegion newRegion){
        repository.save(newRegion);
    }

    @RequestMapping(value = "/forRestaurant", method=RequestMethod.POST)
    public List<RestaurantRegion> getforRestaurant(@RequestBody Restaurant restaurant){
        return repository.findByRestaurant(restaurant);
    }
}
