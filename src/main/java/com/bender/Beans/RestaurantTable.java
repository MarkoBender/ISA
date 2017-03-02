package com.bender.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Nikola on 26-02-17.
 */
@Entity
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long restaurant_table_id;

    private double xvalue;

    private double yvalue;

    private double width;

    private double height;

    //@JsonIgnore
    @ManyToOne
    private RestaurantRegion restaurantRegion;

    //@JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    public RestaurantTable(){}

    public RestaurantTable(long restaurant_table_id, double xvalue, double yvalue, double width, double height, RestaurantRegion restaurantRegion, Restaurant restaurant) {
        this.restaurant_table_id = restaurant_table_id;
        this.xvalue = xvalue;
        this.yvalue = yvalue;
        this.width = width;
        this.height = height;
        this.restaurantRegion = restaurantRegion;
        this.restaurant = restaurant;
    }

    public long getRestaurant_table_id() {
        return restaurant_table_id;
    }

    public void setRestaurant_table_id(long restaurant_table_id) {
        this.restaurant_table_id = restaurant_table_id;
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
