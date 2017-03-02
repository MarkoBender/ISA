package com.bender.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Nikola on 26-02-17.
 */
@Entity
public class RestaurantRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long restaurant_region_id;

    private String name;

    private double xvalue;

    private double yvalue;

    private double width;

    private double height;

    private String color;

    //@JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    public RestaurantRegion(){}

    public RestaurantRegion(long restaurant_region_id, String name, double xvalue, double yvalue, double width, double height, String color, Restaurant restaurant) {
        this.restaurant_region_id = restaurant_region_id;
        this.name = name;
        this.xvalue = xvalue;
        this.yvalue = yvalue;
        this.width = width;
        this.height = height;
        this.color = color;
        this.restaurant = restaurant;
    }

    public long getRestaurant_region_id() {
        return restaurant_region_id;
    }

    public void setRestaurant_region_id(long restaurant_region_id) {
        this.restaurant_region_id = restaurant_region_id;
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
