package com.bender;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Bender on 12/18/2016.
 */
@Entity
public class Restoran {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    //Column
    private String name;
    private String descritption;
    private int distance;


    public Restoran(){}

    public Restoran(String name, String descritption, int distance) {
        this.name = name;
        this.descritption = descritption;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritption() {
        return descritption;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public long getId(){
        return id;
    }


}
