package com.senlainc.gitcourses.kashko.raman.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Column(name = "date_arrival")
    private LocalDate dateArrival;
    @Column(name = "date_departure")
    private LocalDate dateDeparture;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Guest guest;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "handle_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "handle_id"))
    private List<Handling> handling;

    public Order(LocalDate dateArrival, LocalDate dateDeparture, Guest guest, Room room, List<Handling> handling) {
        this.dateArrival = dateArrival;
        this.dateDeparture = dateDeparture;
        this.guest = guest;
        this.room = room;
        this.id = id;
        this.handling = handling;
    }

    public Order() {

    }

    public LocalDate getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(LocalDate dateArrival) {
        this.dateArrival = dateArrival;
    }

    public LocalDate getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(LocalDate dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Handling> getHandling() {
        return handling;
    }

    public void setHandling(List<Handling> handling) {
        this.handling = handling;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateArrival=" + dateArrival +
                ", dateDeparture=" + dateDeparture +
                ", guestId='" + guest + '\'' +
                ", roomId='" + room + '\'' +
                ", id='" + id + '\'' +
                ", handlingId='" + handling + '\'' +
                '}';
    }
}
