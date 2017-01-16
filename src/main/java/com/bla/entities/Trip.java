package com.bla.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by softi on 16.01.2017.
 */
@Table(name = "trips", schema = "public", catalog = "carcarbla")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trips_id_sequence")
    @SequenceGenerator(name = "trips_id_sequence", sequenceName = "trips_id_seq", allocationSize = 1)
    private int id;
    @ManyToOne(targetEntity = Driver.class)
    private Driver driver;
    private Automobile auto;
    @ManyToMany(cascade = CascadeType.ALL)
            @JoinTable(name = "passengers_trips", joinColumns = @JoinColumn(name = "trip_id"), inverseJoinColumns =
            @JoinColumn(name = "passenger_id"))
    List<Passenger> passengerList;
    private String departure;
    private String destination;
    private Date date;
    private int price;
    private int count; //количество пассажиров
    private String status;
    private String info; //информация и доп. условия

    public Trip() {

    }

    public Trip(Driver driver, Automobile auto, String departure, String destination, Date date, int price, int count, String status, String info) {
        this.driver = driver;
        this.auto = auto;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.price = price;
        this.count = count;
        this.status = status;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Automobile getAuto() {
        return auto;
    }

    public void setAuto(Automobile auto) {
        this.auto = auto;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
