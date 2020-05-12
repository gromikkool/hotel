package com.senlainc.gitcourses.kashko.raman.api.service;

import com.senlainc.gitcourses.kashko.raman.dto.RoomDTO;
import com.senlainc.gitcourses.kashko.raman.dto.RoomStatusDTO;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;

import java.util.List;

public interface RoomService {

    void addRoom(RoomDTO room) throws ObjectAlreadyExistsException;

    List<RoomDTO> getAllRooms();

    List<RoomDTO> getFreeRooms();

    List<RoomDTO> getRoomListSort(String sortBy);

    List<RoomDTO> getFreeRoomListSort(String sortBy);

    long getCountFreeRooms();

    RoomDTO getRoomById(Integer id) throws ObjectNotFoundException;

    void changeStatusOfRoom(Integer id, RoomStatusDTO status) throws ObjectNotFoundException;

}
