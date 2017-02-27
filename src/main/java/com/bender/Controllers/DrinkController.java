package com.bender.Controllers;

import com.bender.Beans.Drink;
import com.bender.Beans.Restaurant;
import com.bender.Repositories.DrinkRepository;
import com.bender.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikola on 27-02-17.
 */
@RestController
@RequestMapping(value="/drinks")
public class DrinkController {

    private DrinkRepository repository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public DrinkController(DrinkRepository repository,RestaurantRepository restaurantRepository){
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Drink> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/menu/{id}")
    public List<Drink> getMenu(@PathVariable long id){
        Restaurant myrestaurant = restaurantRepository.findOne(id);
        List<Drink> mymenu = repository.findByRestaurant(myrestaurant);
        return mymenu;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Drink newdrink) {
        newdrink.setTip("Drink");
        repository.save(newdrink);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        repository.delete(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Drink updatedDrink) {
        Drink drink = updatedDrink;
        //dish.setDish_id(id);
        repository.save(drink);
    }
}
