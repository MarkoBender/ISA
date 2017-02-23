package com.bender.Beans;

import javax.persistence.*;

/**
 * Created by Bender on 1/5/2017.
 */
@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long invitation_id;

    @ManyToOne
    private Reservation reservation;

    @ManyToOne
    private Guest invited;

    private boolean confirmed;

    public Invitation(){}

    public Invitation(long invitation_id, Reservation reservation, Guest invited, boolean confirmed) {
        this.invitation_id = invitation_id;
        this.reservation = reservation;
        this.invited = invited;
        this.confirmed = confirmed;
    }

    public long getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(long invitation_id) {
        this.invitation_id = invitation_id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Guest getInvited() {
        return invited;
    }

    public void setInvited(Guest invited) {
        this.invited = invited;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
