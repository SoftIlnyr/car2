package com.bla.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by softi on 16.01.2017.
 */
@Table(name = "passengers", schema = "public", catalog = "carcarbla")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passengers_id_sequence")
    @SequenceGenerator(name = "passengers_id_sequence", sequenceName = "passengers_id_seq", allocationSize = 1)
    private int id;
    private int rating;
    @OneToOne(targetEntity = User.class, mappedBy = "passengerList")
    private User user;
    @ManyToMany(targetEntity = Trip.class)
    private List<Trip> trips;

    public Passenger() {

    }

    public Passenger(int rating, User user) {
        this.rating = rating;
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
