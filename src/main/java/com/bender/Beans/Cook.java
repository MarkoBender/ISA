package com.bender.Beans;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Nikola on 16-02-17.
 */
@Entity
public class Cook extends Employee {

    @ManyToOne
    private DishType dishType;

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }
}
