package com.bender.Beans;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User on 2/26/2017.
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Employee extends User {

    @Column
    private Date birthDate;

    @Column
    private String pantsSize;

    @Column
    private String shoeSize;

    @Column
    private boolean firstLog;

    @ManyToOne
    private Restaurant restaurant;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPantsSize() {
        return pantsSize;
    }

    public void setPantsSize(String pantsSize) {
        this.pantsSize = pantsSize;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public boolean isFirstLog() {
        return firstLog;
    }

    public void setFirstLog(boolean firstLog) {
        this.firstLog = firstLog;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
