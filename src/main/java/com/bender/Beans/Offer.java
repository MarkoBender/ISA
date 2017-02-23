package com.bender.Beans;

import javax.persistence.*;

/**
 * Created by Nikola on 14-02-17.
 */
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long offer_id;

    @ManyToOne
    private Salesman salesman;

    @ManyToOne
    private BuyingOrder myorder;

    private String price;

    private String description;

    private String delivery_deadline;

    private String accepted;

    public Offer(){}

    public Offer(long offer_id, Salesman salesman, BuyingOrder myorder, String price, String description, String delivery_deadline, String accepted) {
        this.offer_id = offer_id;
        this.salesman = salesman;
        this.myorder = myorder;
        this.price = price;
        this.description = description;
        this.delivery_deadline = delivery_deadline;
        this.accepted = accepted;
    }

    public long getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(long offer_id) {
        this.offer_id = offer_id;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDelivery_deadline() {
        return delivery_deadline;
    }

    public void setDelivery_deadline(String delivery_deadline) {
        this.delivery_deadline = delivery_deadline;
    }

    public BuyingOrder getMyorder() {
        return myorder;
    }

    public void setMyorder(BuyingOrder myorder) {
        this.myorder = myorder;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }
}