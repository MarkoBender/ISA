package com.bender.Models;

import com.bender.Beans.Restaurant;

import javax.persistence.*;

/**
 * Created by User on 2/25/2017.
 */
//@Entity
public class RestaurantRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long restaurantRegion_id;

    @Column
    private String name;

    //lazy, all
    @ManyToOne
    private Restaurant restaurant;

    public RestaurantRegion(){}

    public RestaurantRegion(long restaurantRegion_id, String name, Restaurant restaurant) {
        this.restaurantRegion_id = restaurantRegion_id;
        this.name = name;
        this.restaurant = restaurant;
    }

    public long getRestaurantRegion_id() {
        return restaurantRegion_id;
    }

    public void setRestaurantRegion_id(long restaurantRegion_id) {
        this.restaurantRegion_id = restaurantRegion_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
