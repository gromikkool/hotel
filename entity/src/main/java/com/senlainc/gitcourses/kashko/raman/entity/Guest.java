package com.senlainc.gitcourses.kashko.raman.entity;

import javax.persistence.*;

@Entity
@Table(name = "guests")
public class Guest {
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String adress;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Guest() {
    }

    public Guest(String clientName, String phoneNumber, String adress) {
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String name) {
        this.clientName = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String address) {
        this.adress = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "clientName='" + clientName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + adress + '\'' +
                ", id=" + id +
                '}';
    }
}
