package com.bender.Beans;

import javax.persistence.*;

/**
 * Created by Nikola on 14-02-17.
 */
@Entity
public class Dish extends FoodDrinkItem{

    @ManyToOne
    private DishType dish_type;

    public DishType getDish_type() {
        return dish_type;
    }

    public void setDish_type(DishType dish_type) {
        this.dish_type = dish_type;
    }

}
