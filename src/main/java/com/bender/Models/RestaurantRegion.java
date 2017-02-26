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

    @Column
    private double xvalue;

    @Column
    private double yvalue;

    @Column
    private double width;

    @Column
    private double height;

    @Column
    private String color;

    //lazy, all
    @ManyToOne
    private Restaurant restaurant;

    public RestaurantRegion(){}

    public RestaurantRegion(long restaurantRegion_id, String name, double xvalue, double yvalue, double width, double height, String color, Restaurant restaurant) {
        this.restaurantRegion_id = restaurantRegion_id;
        this.name = name;
        this.xvalue = xvalue;
        this.yvalue = yvalue;
        this.width = width;
        this.height = height;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
