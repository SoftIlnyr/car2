package com.bla.entities;

import javax.persistence.*;

/**
 * Created by softi on 12.01.2017.
 */
@Entity
@Table(name = "users", schema = "public", catalog = "carcarbla")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_secuence")
    @SequenceGenerator(name = "user_id_secuence", sequenceName = "users_id_seq", allocationSize = 1)
    private int id;
    private String nickname;
    private String password;
    private String firstname;
    private String surname;
    private String avatar;
    private String email;
    private String role;
    @OneToOne(targetEntity = Driver.class, mappedBy = "user")
    private Driver driver;
    @OneToOne(targetEntity = Passenger.class, mappedBy = "user")
    private Driver passenger;

    public User(){    }

    public User(String nickname, String password, String firstname, String surname, String email, String role, String avatar) {
        this.nickname = nickname;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.avatar = avatar;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getPassenger() {
        return passenger;
    }

    public void setPassenger(Driver passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "User{" +
                ", nickname='" + nickname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
