package com.bender.Beans;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by Kuka on 2/14/2017.
 */
@Entity
public class RestaurantManager extends User{

    @OneToOne
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
