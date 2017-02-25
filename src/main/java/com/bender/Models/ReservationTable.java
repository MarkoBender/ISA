package com.bender.Models;

import com.bender.Beans.Reservation;
import com.bender.Beans.Restaurant;

import javax.persistence.*;

/**
 * Created by User on 2/25/2017.
 */
//@Entity
public class ReservationTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long reservationTable_id;

    //persist, eager
    @ManyToOne
    private Reservation reservation;

    //persist, eager
    @ManyToOne
    private RestaurantTable restaurantTable;

    public ReservationTable() {
    }

    public ReservationTable(long reservationTable_id, Reservation reservation, RestaurantTable restaurantTable) {
        this.reservationTable_id = reservationTable_id;
        this.reservation = reservation;
        this.restaurantTable = restaurantTable;
    }

    public long getReservationTable_id() {
        return reservationTable_id;
    }

    public void setReservationTable_id(long reservationTable_id) {
        this.reservationTable_id = reservationTable_id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }
}
