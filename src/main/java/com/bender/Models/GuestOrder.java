package com.bender.Models;

import com.bender.Beans.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/25/2017.
 */
//@Entity
public class GuestOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long guestOrder_id;

    @OneToOne
    private Steward steward;

    @Column
    private String status;

    @OneToMany
    private List<OrderItem> orderItems;

    @OneToMany
    private List<RestaurantTable> reservationTable;

    @OneToOne
    private Reservation reservation;

    @ManyToOne
    private Restaurant restaurant;

    public GuestOrder() {
    }

    public GuestOrder(long guestOrder_id, Steward steward, String status, List<OrderItem> orderItems, List<RestaurantTable> reservationTable, Reservation reservation, Restaurant restaurant) {
        this.guestOrder_id = guestOrder_id;
        this.steward = steward;
        this.status = status;
        this.orderItems = orderItems;
        this.reservationTable = reservationTable;
        this.reservation = reservation;
        this.restaurant = restaurant;
    }

    public long getGuestOrder_id() {
        return guestOrder_id;
    }

    public void setGuestOrder_id(long guestOrder_id) {
        this.guestOrder_id = guestOrder_id;
    }

    public Steward getSteward() {
        return steward;
    }

    public void setSteward(Steward steward) {
        this.steward = steward;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<RestaurantTable> getReservationTable() {
        return reservationTable;
    }

    public void setReservationTable(List<RestaurantTable> reservationTable) {
        this.reservationTable = reservationTable;
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
}
