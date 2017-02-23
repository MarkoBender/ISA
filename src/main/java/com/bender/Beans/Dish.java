package com.bender.Beans;

import javax.persistence.*;

/**
 * Created by Nikola on 14-02-17.
 */
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long dish_id;

    @ManyToOne
    private DishType dish_type;

    private String name;

    private String description;

    private double cena;

    @ManyToOne
    private Restaurant restaurant;

    public Dish(){}

    public Dish(long dish_id, DishType dish_type, String name, String description, double cena, Restaurant restaurant) {
        this.dish_id = dish_id;
        this.dish_type = dish_type;
        this.name = name;
        this.description = description;
        this.cena = cena;
        this.restaurant = restaurant;
    }

    public long getDish_id() {
        return dish_id;
    }

    public void setDish_id(long dish_id) {
        this.dish_id = dish_id;
    }

    public DishType getDish_type() {
        return dish_type;
    }

    public void setDish_type(DishType dish_type) {
        this.dish_type = dish_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
