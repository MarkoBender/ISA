package com.bender.Beans;

import javax.persistence.*;

/**
 * Created by Nikola on 14-02-17.
 */
@Entity
public class DrinkType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long drinktype_id;

    private String name;

    public DrinkType(){};

    public DrinkType(long drinktype_id, String name) {
        this.drinktype_id = drinktype_id;
        this.name = name;
    }

    public long getDrinktype_id() {
        return drinktype_id;
    }

    public void setDrinktype_id(long drinktype_id) {
        this.drinktype_id = drinktype_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
