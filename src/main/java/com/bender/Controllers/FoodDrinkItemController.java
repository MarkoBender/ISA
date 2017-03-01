package com.bender.Controllers;

import com.bender.Beans.FoodDrinkItem;
import com.bender.Beans.Restaurant;
import com.bender.Repositories.EmployeeRepository;
import com.bender.Repositories.FoodDrinkItemRepository;
import com.bender.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by User on 2/26/2017.
 */
@RestController
@RequestMapping(value="/foodndrinks")
public class FoodDrinkItemController {

    private FoodDrinkItemRepository repository;
    private RestaurantRepository restaurantRepository;
    @Autowired
    public FoodDrinkItemController (FoodDrinkItemRepository repository,RestaurantRepository restaurantRepository){
        this.repository=repository;
        this.restaurantRepository = restaurantRepository;
    }

    @RequestMapping(value="itemFromName/{productname}",method= RequestMethod.POST)
    public FoodDrinkItem itemfromName(@PathVariable String productname, @RequestBody Restaurant restaurant){
        FoodDrinkItem myitem = null;
        System.out.print("SADASDSADADA");
        for(FoodDrinkItem item : repository.findAll()){
            if(item.getRestaurant().getRestaurant_id() == restaurant.getRestaurant_id()){
                if(item.getName().toLowerCase().equals(productname.toLowerCase()))
                    myitem = item;
            }
        }

        return myitem;
    }
}
