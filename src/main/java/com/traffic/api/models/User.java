package com.traffic.api.models;


import javax.persistence.*;


@Entity
@Table(name = "meteo_users")
public class User {
    @Id
    @Column(name = "id",unique=true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String phone;
    private boolean telegram;


    public User() {
    }

    public User(String password, String role, String email, String phone, boolean telegram) {
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.telegram = telegram;
    }

    public User(String username, String password, String role, String email, String phone, boolean telegram) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.telegram = telegram;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isTelegram() {
        return telegram;
    }

    public void setTelegram(boolean telegram) {
        this.telegram = telegram;
    }
}