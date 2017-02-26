package com.bender.Models;

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
    private FoodDrinkItem foodDrinkItem;

    @Column
    private int amount;

    @Column
    private String status;

    public OrderItem(){}

    public OrderItem(long orderItem_id, FoodDrinkItem foodDrinkItem, int amount, String status) {
        this.orderItem_id = orderItem_id;
        this.foodDrinkItem = foodDrinkItem;
        this.amount = amount;
        this.status = status;
    }

    public long getOrderItem_id() {
        return orderItem_id;
    }

    public void setOrderItem_id(long orderItem_id) {
        this.orderItem_id = orderItem_id;
    }

    public FoodDrinkItem getFoodDrinkItem() {
        return foodDrinkItem;
    }

    public void setFoodDrinkItem(FoodDrinkItem foodDrinkItem) {
        this.foodDrinkItem = foodDrinkItem;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
