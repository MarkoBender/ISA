package com.bender.Controllers;

import com.bender.Beans.Restaurant;
import com.bender.Beans.RestaurantTable;
import com.bender.Repositories.RestaurantTableRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikola on 26-02-17.
 */
@RestController
@RequestMapping(value="/restauranttables")
public class RestaurantTableController {

    private RestaurantTableRepository repository;

    public RestaurantTableController (RestaurantTableRepository repository){

        this.repository = repository;

    }
    @RequestMapping(value = "/all")
    public List<RestaurantTable> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody RestaurantTable newTable){
        repository.save(newTable);
    }

    @RequestMapping(value = "/forRestaurant", method=RequestMethod.POST)
    public List<RestaurantTable> getforRestaurant(@RequestBody Restaurant restaurant) {
        return repository.findByRestaurant(restaurant);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        repository.delete(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody RestaurantTable updatedTable) {
        RestaurantTable table=updatedTable;
        table.setRestaurant_table_id(id);
        repository.save(table);
    }
}

