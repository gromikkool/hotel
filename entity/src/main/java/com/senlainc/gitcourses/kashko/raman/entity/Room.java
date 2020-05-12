package com.senlainc.gitcourses.kashko.raman.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Column(name = "number_of_room")
    private Integer numberOfRoom;
    @Column(name = "price_day")
    private Integer pricePerDay;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "status('FREE', 'BOOKED', 'REPAIRING', 'BUSY')")
    private RoomStatus status; //Free, repairing, booked
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer capacity;
    private Integer stars;
    @OneToMany
    private List<Order> orders;


    public Room(Integer numberOfRoom, Integer pricePerDay, RoomStatus status, Integer capacity, Integer stars) {
        this.numberOfRoom = numberOfRoom;
        this.pricePerDay = pricePerDay;
        this.status = status;
        this.id = id;
        this.capacity = capacity;
        this.stars = stars;
    }

    public Room() {

    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(Integer numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Room{" +
                "numberOfRoom=" + numberOfRoom +
                ", price=" + pricePerDay +
                ", status=" + status +
                ", id=" + id +
                ", capacity=" + capacity +
                ", stars=" + stars;
    }
}
