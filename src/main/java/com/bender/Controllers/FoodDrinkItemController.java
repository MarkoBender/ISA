package com.bender.Controllers;

import com.bender.Beans.FoodDrinkItem;
import com.bender.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by User on 2/26/2017.
 */
public class FoodDrinkItemController {

    private FoodDrinkItem repository;

    @Autowired
    public FoodDrinkItemController (FoodDrinkItem repository){this.repository=repository;}
}
