package com.bender.Controllers;

import com.bender.Beans.Dish;
import com.bender.Beans.Restaurant;
import com.bender.Repositories.DishRepository;
import com.bender.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikola on 16-02-17.
 */
@RestController
@RequestMapping(value="/dishes")
public class DishController {

    private DishRepository repository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public DishController(DishRepository repository,RestaurantRepository restaurantRepository){
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @RequestMapping(value = "/menu/{id}")
    public List<Dish> getMenu(@PathVariable long id){
        Restaurant myrestaurant = restaurantRepository.findOne(id);
        List<Dish> mymenu = repository.findByRestaurant(myrestaurant);
        return mymenu;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Dish newdish) {
        repository.save(newdish);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        repository.delete(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Dish updatedDish) {
        Dish dish = updatedDish;
        //dish.setDish_id(id);
        repository.save(dish);
    }
}
