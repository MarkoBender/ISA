package com.bender.Models;

import com.bender.Beans.Steward;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/25/2017.
 */
//@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long bill_id;

    @Column
    private double price;

    @Column
    private Date date;

    @ManyToOne
    private Steward steward;

    @OneToOne
    private List<GuestOrder> orders;

    public Bill(){}

    public Bill(long bill_id, double price, Date date, Steward steward, List<GuestOrder> orders) {
        this.bill_id = bill_id;
        this.price = price;
        this.date = date;
        this.steward = steward;
        this.orders = orders;
    }

    public long getBill_id() {
        return bill_id;
    }

    public void setBill_id(long bill_id) {
        this.bill_id = bill_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Steward getSteward() {
        return steward;
    }

    public void setSteward(Steward steward) {
        this.steward = steward;
    }

    public List<GuestOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<GuestOrder> orders) {
        this.orders = orders;
    }
}