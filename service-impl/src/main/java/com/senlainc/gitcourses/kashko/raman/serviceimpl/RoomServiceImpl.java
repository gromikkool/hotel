package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.RoomRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.RoomService;
import com.senlainc.gitcourses.kashko.raman.dto.RoomDTO;
import com.senlainc.gitcourses.kashko.raman.dto.RoomStatusDTO;
import com.senlainc.gitcourses.kashko.raman.entity.Room;
import com.senlainc.gitcourses.kashko.raman.entity.RoomStatus;
import com.senlainc.gitcourses.kashko.raman.exceptions.DaoException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = Logger.getLogger(RoomServiceImpl.class);

    private final RoomRepository roomRepository;
    private ModelMapper modelMapper;
    private Boolean changeStatus;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    public void addRoom(RoomDTO roomDTO) {
        try {
            Room room = modelMapper.map(roomDTO, Room.class);
            roomRepository.save(room);
        } catch (DaoException e) {
            LOGGER.log(Level.WARN, "can't save room", e);
        }

    }

    @Override
    public List<RoomDTO> getAllRooms() {
        try {
            return roomRepository.getAll().stream()
                    .map(x -> modelMapper.map(x, RoomDTO.class))
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            return null;
        }
    }

    @Override
    public List<RoomDTO> getFreeRooms() {
        return roomRepository.getFreeRoomList().stream()
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getRoomListSort(String sortBy) {
        return roomRepository.getRoomListSort(sortBy).stream()
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<RoomDTO> getFreeRoomListSort(String sortBy) {
        return roomRepository.getFreeRoomListSort(sortBy).stream()
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());
    }

    public RoomDTO getRoomById(Integer id) {
        try {
            Room room = roomRepository.getById(id);
            return modelMapper.map(room, RoomDTO.class);
        } catch (DaoException | ObjectNotFoundException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
            return null;
        }
    }

    @Override
    public void changeStatusOfRoom(Integer id, RoomStatusDTO status) {
        if (changeStatus) {
            getRoomById(id).setStatus(modelMapper.map(status, RoomStatusDTO.class));
        } else {
            System.out.println("unable to change status right now");
        }
    }

    @Override
    public long getCountFreeRooms() {
        return roomRepository.countFreeRooms();
    }

}
