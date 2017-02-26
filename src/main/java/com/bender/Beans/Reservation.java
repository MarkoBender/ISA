package com.bender.Beans;

import com.bender.Models.OrderItem;
import com.bender.Models.RestaurantTable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Created by Bender on 1/5/2017.
 */
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long reservation_id;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Guest host;

    private Date dateTime;

    private int duration;

    @OneToMany
    private List<RestaurantTable> reservationTables;

    @OneToMany
    private List<OrderItem> orderItems;

    public Reservation(){}

    public Reservation(long reservation_id, Restaurant restaurant, Guest host, Date dateTime, int duration, List<RestaurantTable> reservationTables, List<OrderItem> orderItems) {
        this.reservation_id = reservation_id;
        this.restaurant = restaurant;
        this.host = host;
        this.dateTime = dateTime;
        this.duration = duration;
        this.reservationTables = reservationTables;
        this.orderItems = orderItems;
    }

    public long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Guest getHost() {
        return host;
    }

    public void setHost(Guest host) {
        this.host = host;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<RestaurantTable> getReservationTables() {
        return reservationTables;
    }

    public void setReservationTables(List<RestaurantTable> reservationTables) {
        this.reservationTables = reservationTables;
    }


}
