package com.bender.Beans;

import com.bender.Beans.Guest;
import com.bender.Beans.Reservation;

import javax.persistence.*;

/**
 * Created by User on 3/1/2017.
 */
@Entity
public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long ocena_id;

    @ManyToOne
    private Reservation reservation;

    @ManyToOne
    private Guest guest;

    @Column
    private double ocenaRestorana;

    @Column
    private double ocenaUsluge;

    public Ocena(){}

    public Ocena(long ocena_id, Reservation reservation, Guest guest, double ocenaRestorana, double ocenaUsluge) {
        this.ocena_id = ocena_id;
        this.reservation = reservation;
        this.guest = guest;
        this.ocenaRestorana = ocenaRestorana;
        this.ocenaUsluge = ocenaUsluge;
    }

    public long getOcena_id() {
        return ocena_id;
    }

    public void setOcena_id(long ocena_id) {
        this.ocena_id = ocena_id;
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

    public double getOcenaRestorana() {
        return ocenaRestorana;
    }

    public void setOcenaRestorana(double ocenaRestorana) {
        this.ocenaRestorana = ocenaRestorana;
    }

    public double getOcenaUsluge() {
        return ocenaUsluge;
    }

    public void setOcenaUsluge(double ocenaUsluge) {
        this.ocenaUsluge = ocenaUsluge;
    }
}
