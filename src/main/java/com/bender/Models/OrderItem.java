package com.bender.Models;

import com.bender.Beans.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User on 2/25/2017.
 */
//@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long orderItem_id;

    @Column
    private Date date;

    @ManyToOne
    private Dish dish;

    @ManyToOne
    private Drink drink;

    @Column
    private String statusOfDrink;

    @Column
    private String statusOfDish;

    @ManyToOne
    private Reservation reservation;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Steward steward;

    @ManyToOne
    private Cook cook;

    public OrderItem(){}

    public OrderItem(long orderItem_id, Date date, Dish dish, Drink drink, String statusOfDrink, String statusOfDish, Reservation reservation, Restaurant restaurant, Guest guest, Steward steward, Cook cook) {
        this.orderItem_id = orderItem_id;
        this.date = date;
        this.dish = dish;
        this.drink = drink;
        this.statusOfDrink = statusOfDrink;
        this.statusOfDish = statusOfDish;
        this.reservation = reservation;
        this.restaurant = restaurant;
        this.guest = guest;
        this.steward = steward;
        this.cook = cook;
    }

    public long getOrderItem_id() {
        return orderItem_id;
    }

    public void setOrderItem_id(long orderItem_id) {
        this.orderItem_id = orderItem_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public String getStatusOfDrink() {
        return statusOfDrink;
    }

    public void setStatusOfDrink(String statusOfDrink) {
        this.statusOfDrink = statusOfDrink;
    }

    public String getStatusOfDish() {
        return statusOfDish;
    }

    public void setStatusOfDish(String statusOfDish) {
        this.statusOfDish = statusOfDish;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Steward getSteward() {
        return steward;
    }

    public void setSteward(Steward steward) {
        this.steward = steward;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }
}
