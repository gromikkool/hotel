package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.service.RoomService;
import com.senlainc.gitcourses.kashko.raman.dto.RoomDTO;
import com.senlainc.gitcourses.kashko.raman.dto.RoomStatusDTO;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addRoom(@RequestBody RoomDTO roomDTO) throws ObjectAlreadyExistsException {
        roomService.addRoom(roomDTO);
    }
    @GetMapping
    public List<RoomDTO> getAllRooms()
    {
        return roomService.getAllRooms();
    }
    @GetMapping(value = "/freeRooms")
    public List<RoomDTO> getFreeRooms() {
        return roomService.getFreeRooms();
    }
    @GetMapping(value = "/sort/{sortBy}")
    public List<RoomDTO> getRoomListSort(String sortBy)
    {
        return roomService.getRoomListSort(sortBy);
    }
    @GetMapping(value = "/{id}")
    public RoomDTO getRoomById(@PathVariable Integer id) throws ObjectNotFoundException {
        return roomService.getRoomById(id);
    }
    @PatchMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeStatusOfRoom(@PathVariable Integer id, @PathVariable RoomStatusDTO status) throws ObjectNotFoundException {
        roomService.changeStatusOfRoom(id, status);
    }
    @GetMapping(value = "/count")
    public long getCountFreeRooms()
    {
        return roomService.getCountFreeRooms();
    }
}
