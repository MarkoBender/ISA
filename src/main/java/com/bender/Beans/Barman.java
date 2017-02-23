package com.bender.Beans;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Nikola on 16-02-17.
 */
@Entity
public class Barman extends User {

    private Date birthDate;

    private String pantsSize;

    private String shoeSize;

    private boolean firstLog;

    @ManyToOne
    private Restaurant restaurant;

    public boolean isFirstLog() {
        return firstLog;
    }

    public void setFirstLog(boolean firstLog) {
        this.firstLog = firstLog;
    }

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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
