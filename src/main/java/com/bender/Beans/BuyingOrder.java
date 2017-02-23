package com.bender.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikola on 14-02-17.
 */
@Entity
public class BuyingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long buying_order_id;

    @ManyToOne
    private Restaurant restaurant;

    private Date active_from;

    private Date active_until;

    private String order_description;

    private String status;

    public BuyingOrder(){}

    public BuyingOrder(long buying_order_id, Restaurant restaurant, Date active_from, Date active_until, String order_description, String active) {
        this.buying_order_id = buying_order_id;
        this.restaurant = restaurant;
        this.active_from = active_from;
        this.active_until = active_until;
        this.order_description = order_description;
        this.status = active;
    }

    public long getBuying_order_id() {
        return buying_order_id;
    }

    public void setBuying_order_id(long buying_order_id) {
        this.buying_order_id = buying_order_id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getActive_from() {
        return active_from;
    }

    public void setActive_from(Date active_from) {
        this.active_from = active_from;
    }

    public Date getActive_until() {
        return active_until;
    }

    public void setActive_until(Date active_until) {
        this.active_until = active_until;
    }

    public String getOrder_description() {
        return order_description;
    }

    public void setOrder_description(String order_description) {
        this.order_description = order_description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}