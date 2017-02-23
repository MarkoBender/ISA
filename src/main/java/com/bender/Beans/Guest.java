package com.bender.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.awt.*;
import java.sql.Blob;
import java.util.List;

/**
 * Created by Bender on 12/25/2016.
 */
@Entity
public class Guest extends User {

    @Column
    private int visits;

    @JsonIgnore
    @ManyToMany
    private List<Guest> friends;

    @JsonIgnore
    @ManyToMany
    private List<Guest> requests;

    @Column(nullable = true)
    private Blob image;

    @Column(nullable = true)
    private String adress;

    public List<Guest> getRequests() {
        return requests;
    }

    public void setRequests(List<Guest> requests) {
        this.requests = requests;
    }

    public List<Guest> getFriends() {
        return friends;
    }

    public void setFriends(List<Guest> friends) {
        this.friends = friends;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
