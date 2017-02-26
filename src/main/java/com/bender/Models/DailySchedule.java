package com.bender.Models;

import com.bender.Beans.Restaurant;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User on 2/26/2017.
 */
//@Entity
public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long dailySchedule_id;

    @ManyToOne
    private Employee employee;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @ManyToOne
    private RestaurantRegion restaurantRegion;

    @ManyToOne
    private Restaurant restaurant;

    public DailySchedule(){}

    public DailySchedule(long dailySchedule_id, Employee employee, Date startDate, Date endDate, RestaurantRegion restaurantRegion, Restaurant restaurant) {
        this.dailySchedule_id = dailySchedule_id;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.restaurantRegion = restaurantRegion;
        this.restaurant = restaurant;
    }

    public long getDailySchedule_id() {
        return dailySchedule_id;
    }

    public void setDailySchedule_id(long dailySchedule_id) {
        this.dailySchedule_id = dailySchedule_id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public RestaurantRegion getRestaurantRegion() {
        return restaurantRegion;
    }

    public void setRestaurantRegion(RestaurantRegion restaurantRegion) {
        this.restaurantRegion = restaurantRegion;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
