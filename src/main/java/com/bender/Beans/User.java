package com.bender.Beans;

import javax.persistence.*;

/**
 * Created by Bender on 12/25/2016.
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long user_id;

    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;

    @Column
    private String uloga;

    public User(){}

    public User(long user_id, String email, String password, String name, String surname, String uloga) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.uloga = uloga;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}
