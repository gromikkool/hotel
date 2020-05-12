package com.senlainc.gitcourses.kashko.raman.api.repository;

import com.senlainc.gitcourses.kashko.raman.entity.Room;

import java.util.List;

public interface RoomRepository extends GenericDAO<Room, Integer> {
    List<Room> getRoomListSort(String sortBy);

    List<Room> getFreeRoomList();

    List<Room> getFreeRoomListSort(String sortBy);

    long countFreeRooms();

}
