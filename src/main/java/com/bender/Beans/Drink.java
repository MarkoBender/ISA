package com.bender.Beans;

import javax.persistence.*;

/**
 * Created by Nikola on 14-02-17.
 */
@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long drink_id;

    @ManyToOne
    private  DrinkType type;

    private String name;

    private double cena;

    @ManyToOne
    private Restaurant restaurant;

    public Drink(){}



    public long getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(long drink_id) {
        this.drink_id = drink_id;
    }

    public DrinkType getType() {
        return type;
    }

    public void setType(DrinkType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}