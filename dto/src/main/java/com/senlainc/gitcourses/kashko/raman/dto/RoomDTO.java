package com.senlainc.gitcourses.kashko.raman.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.senlainc.gitcourses.kashko.raman.entity.Order;
import com.senlainc.gitcourses.kashko.raman.entity.RoomStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {
    private Integer numberOfRoom;
    private Integer pricePerDay;
    private RoomStatusDTO status; //Free, repairing, booked
    private Integer id;
    private Integer capacity;
    private Integer stars;

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

    public RoomStatusDTO getStatus() {
        return status;
    }

    public void setStatus(RoomStatusDTO status) {
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
}
