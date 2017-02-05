package com.bla.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "drivers", schema = "public", catalog = "carcarbla")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_id_generator")
    @SequenceGenerator(name = "driver_id_generator", sequenceName = "drivers_id_seq")
    private int id;
    private int experience;
    private int rating;
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private User user;
    @OneToMany(targetEntity = Automobile.class, mappedBy = "driver", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private List<Automobile> automobileList;
    @OneToMany(mappedBy = "driver")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private List<Trip> trips;

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

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
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
