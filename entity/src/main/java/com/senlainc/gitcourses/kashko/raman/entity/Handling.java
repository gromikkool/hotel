package com.senlainc.gitcourses.kashko.raman.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "handlings")
public class Handling {
    @Column(name = "name_service")
    private String nameService;
    private Integer price;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date_handling")
    private LocalDate Date;

    public Handling(String nameService, Integer price, LocalDate date) {
        this.nameService = nameService;
        this.price = price;
        this.id = id;
        this.Date = date;
    }

    public Handling() {

    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Handling{" +
                "nameService='" + nameService + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", Date=" + Date +
                '}';
    }
}
