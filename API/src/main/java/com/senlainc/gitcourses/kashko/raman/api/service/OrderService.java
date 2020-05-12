package com.senlainc.gitcourses.kashko.raman.api.service;

import com.senlainc.gitcourses.kashko.raman.dto.OrderDTO;
import com.senlainc.gitcourses.kashko.raman.dto.RoomDTO;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO order) throws ObjectAlreadyExistsException, ObjectNotFoundException, IOException;

    OrderDTO getOrderById(Integer id) throws ObjectNotFoundException;

    List<RoomDTO> getRoomsWillBeAvailableAt(LocalDate date) throws ObjectNotFoundException;

    List<OrderDTO> getAllOrders();

    long getPriceForOrder(Integer id) throws ObjectNotFoundException;

    List<OrderDTO> getThreeLastGuests();

    int getPriceForServices(Integer id) throws ObjectNotFoundException;


    void deleteGuestFromRoom(Integer orderId) throws ObjectNotFoundException;

}
