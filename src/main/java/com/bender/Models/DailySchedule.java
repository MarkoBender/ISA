package com.bender.Models;

import com.bender.Beans.Employee;
import com.bender.Beans.Restaurant;
import com.bender.Beans.RestaurantRegion;

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
    private Date day;

    @Column
    private int startHours;

    @Column
    private int startMinutes;

    @Column
    private int endHours;

    @Column
    private int endMinutes;

    @ManyToOne
    private RestaurantRegion restaurantRegion;

    @ManyToOne
    private Restaurant restaurant;

    public DailySchedule(){}

    public DailySchedule(long dailySchedule_id, Employee employee, Date day, int startHours, int startMinutes, int endHours, int endMinutes, RestaurantRegion restaurantRegion, Restaurant restaurant) {
        this.dailySchedule_id = dailySchedule_id;
        this.employee = employee;
        this.day = day;
        this.startHours = startHours;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
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

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getStartHours() {
        return startHours;
    }

    public void setStartHours(int startHours) {
        this.startHours = startHours;
    }

    public int getStartMinutes() {
        return startMinutes;
    }

    public void setStartMinutes(int startMinutes) {
        this.startMinutes = startMinutes;
    }

    public int getEndHours() {
        return endHours;
    }

    public void setEndHours(int endHours) {
        this.endHours = endHours;
    }

    public int getEndMinutes() {
        return endMinutes;
    }

    public void setEndMinutes(int endMinutes) {
        this.endMinutes = endMinutes;
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
