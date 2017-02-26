package com.bender.Beans;

import javax.persistence.*;

/**
 * Created by Nikola on 14-02-17.
 */
@Entity
public class Drink extends FoodDrinkItem{

    @ManyToOne
    private  DrinkType type;

    public DrinkType getType() {
        return type;
    }

    public void setType(DrinkType type) {
        this.type = type;
    }

}