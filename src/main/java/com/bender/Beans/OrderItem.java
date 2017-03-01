package com.bender.Beans;

import com.bender.Beans.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User on 2/25/2017.
 */
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long orderItem_id;

    @Column
    private Date date;

    @ManyToOne
    private FoodDrinkItem foodDrinkItem;

    @Column
    private String status;

    @ManyToOne
    private Reservation reservation;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Steward steward;

    @ManyToOne
    private Cook cook;

    @ManyToOne
    private Barman barman;

    public OrderItem(){}

    public OrderItem(long orderItem_id, Date date, FoodDrinkItem foodDrinkItem, String status, Reservation reservation, Guest guest, Steward steward, Cook cook, Barman barman) {
        this.orderItem_id = orderItem_id;
        this.date = date;
        this.foodDrinkItem = foodDrinkItem;
        this.status = status;
        this.reservation = reservation;
        this.guest = guest;
        this.steward = steward;
        this.cook = cook;
        this.barman = barman;
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

    public FoodDrinkItem getFoodDrinkItem() {
        return foodDrinkItem;
    }

    public void setFoodDrinkItem(FoodDrinkItem foodDrinkItem) {
        this.foodDrinkItem = foodDrinkItem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
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

    public Barman getBarman() {
        return barman;
    }

    public void setBarman(Barman barman) {
        this.barman = barman;
    }
}
