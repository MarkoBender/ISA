package com.bender.Models;

import com.bender.Beans.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by User on 2/25/2017.
 */
//@Entity
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long restaurantTable_id;

    @Column
    private double xvalue;

    @Column
    private double yvalue;

    @Column
    private double width;

    @Column
    private double height;

    @ManyToOne
    private RestaurantRegion restaurantRegion;

    @ManyToOne
    private Restaurant restaurant;

    public RestaurantTable(){}

    public RestaurantTable(long restaurantTable_id, double xvalue, double yvalue, double width, double height, RestaurantRegion restaurantRegion, Restaurant restaurant) {
        this.restaurantTable_id = restaurantTable_id;
        this.xvalue = xvalue;
        this.yvalue = yvalue;
        this.width = width;
        this.height = height;
        this.restaurantRegion = restaurantRegion;
        this.restaurant = restaurant;
    }

    public long getRestaurantTable_id() {
        return restaurantTable_id;
    }

    public void setRestaurantTable_id(long restaurantTable_id) {
        this.restaurantTable_id = restaurantTable_id;
    }

    public double getXvalue() {
        return xvalue;
    }

    public void setXvalue(double xvalue) {
        this.xvalue = xvalue;
    }

    public double getYvalue() {
        return yvalue;
    }

    public void setYvalue(double yvalue) {
        this.yvalue = yvalue;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public RestaurantRegion getRestaurantRegion() {
        return restaurantRegion;
    }

    public void setRestaurantRegion(RestaurantRegion restaurantRegion) {
        this.restaurantRegion = restaurantRegion;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
