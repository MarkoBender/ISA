package com.bender.Models;

import com.bender.Beans.Guest;
import com.bender.Beans.Reservation;
import com.bender.Beans.Restaurant;
import com.bender.Beans.Steward;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User on 2/25/2017.
 */
//@Entity
public class GuestOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long guestOrder_id;

    @Column
    private Date date;

    @Column
    private Date deadLine;

    @ManyToOne
    private ReservationTable reservationTable;

    @OneToOne
    private Steward steward;

    @Column
    private String status;



    @ManyToOne
    private Bill bill;



}
