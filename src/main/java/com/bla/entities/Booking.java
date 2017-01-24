package com.bla.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created by softi on 21.01.2017.
 */
@Entity
@Table(name = "bookings", schema = "public", catalog = "carcarbla")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookings_id_sequence")
    @SequenceGenerator(name = "bookings_id_sequence", sequenceName = "bookings_id_seq", allocationSize = 1)
    private int id;
    @ManyToOne(targetEntity = Trip.class)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private Trip trip;
    @ManyToOne(targetEntity = Passenger.class)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private Passenger passenger;
    private int count;
    private String info;
    private String confirm;

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
