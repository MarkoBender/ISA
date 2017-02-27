package com.bender.Models;

import com.bender.Beans.*;

import javax.persistence.*;

/**
 * Created by User on 2/25/2017.
 */
//@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long orderItem_id;

    @ManyToOne
    private Dish dish;

    @ManyToOne
    private Drink drink;

    @ManyToOne
    private Reservation reservation;

    @Column
    private String statusOfDrink;

    @Column
    private String statusOfDish;

    @ManyToOne
    private Steward steward;

    @ManyToOne
    private Cook cook;

    public OrderItem(){}

    public OrderItem(long orderItem_id, Dish dish, Drink drink, Reservation reservation, String statusOfDrink, String statusOfDish, Steward steward, Cook cook) {
        this.orderItem_id = orderItem_id;
        this.dish = dish;
        this.drink = drink;
        this.reservation = reservation;
        this.statusOfDrink = statusOfDrink;
        this.statusOfDish = statusOfDish;
        this.steward = steward;
        this.cook = cook;
    }

    public long getOrderItem_id() {
        return orderItem_id;
    }

    public void setOrderItem_id(long orderItem_id) {
        this.orderItem_id = orderItem_id;
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
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
