package com.bla.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by softi on 16.01.2017.
 */
@Entity
@Table(name = "drivers", schema = "public", catalog = "carcarbla")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_id_generator")
    @SequenceGenerator(name = "driver_id_generator", sequenceName = "drivers_id_seq")
    private int id;
    private int experience;
    private int rating;
    @OneToOne(targetEntity = User.class, mappedBy = "driver")
    private User user;
    @OneToMany(targetEntity = Automobile.class, mappedBy = "driver")
    private List<Automobile> automobileList;
    @OneToMany(targetEntity = Trip.class, mappedBy = "driver")
    private List<Trip> tripList;

    public Driver() {

    }

    public Driver(int experience, int rating, User user, List<Automobile> automobileList) {
        this.experience = experience;
        this.rating = rating;
        this.user = user;
        this.automobileList = automobileList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Automobile> getAutomobileList() {
        return automobileList;
    }

    public void setAutomobileList(List<Automobile> automobileList) {
        this.automobileList = automobileList;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", rating=" + rating +
                ", experience=" + experience +
                ", user=" + user +
                '}';
    }
}