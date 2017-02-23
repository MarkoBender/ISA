package com.bender.Beans;

import javax.persistence.*;

/**
 * Created by Nikola on 14-02-17.
 */
@Entity
public class DishType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long dishtype_id;

    private String name;

    public DishType(){}

    public DishType(long dishtype_id, String name) {
        this.dishtype_id = dishtype_id;
        this.name = name;
    }

    public long getDishtype_id() {
        return dishtype_id;
    }

    public void setDishtype_id(long dishtype_id) {
        this.dishtype_id = dishtype_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
