package com.bender.Beans;

import com.bender.Beans.Restaurant;

import javax.persistence.*;

/**
 * Created by User on 2/25/2017.
 */
//treba biti isahijerarhija sa hranom i picem, zbog stavki za meni
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class FoodDrinkItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long foodDrinkItem_id;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private String description;

    //lazy, all
    @ManyToOne
    private Restaurant restaurant;

    @Column
    private String tip;

    public FoodDrinkItem(){}

    public FoodDrinkItem(long foodDrinkItem_id, String name, double price, String description, Restaurant restaurant, String tip) {
        this.foodDrinkItem_id = foodDrinkItem_id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.restaurant = restaurant;
        this.tip = tip;
    }

    public long getFoodDrinkItem_id() {
        return foodDrinkItem_id;
    }

    public void setFoodDrinkItem_id(long foodDrinkItem_id) {
        this.foodDrinkItem_id = foodDrinkItem_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
