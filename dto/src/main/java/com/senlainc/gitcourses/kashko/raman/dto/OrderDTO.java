package com.senlainc.gitcourses.kashko.raman.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.senlainc.gitcourses.kashko.raman.entity.Guest;
import com.senlainc.gitcourses.kashko.raman.entity.Handling;
import com.senlainc.gitcourses.kashko.raman.entity.Room;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateArrival;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDeparture;
    private GuestDTO guest;
    private RoomDTO room;
    private Integer id;
    private List<HandlingDTO> handling;

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

    public GuestDTO getGuest() {
        return guest;
    }

    public void setGuest(GuestDTO guest) {
        this.guest = guest;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<HandlingDTO> getHandling() {
        return handling;
    }

    public void setHandling(List<HandlingDTO> handling) {
        this.handling = handling;
    }
}
