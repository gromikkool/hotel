package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.service.OrderService;
import com.senlainc.gitcourses.kashko.raman.dto.OrderDTO;
import com.senlainc.gitcourses.kashko.raman.dto.RoomDTO;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public void saveOrder(@RequestBody OrderDTO orderDTO) throws IOException, ObjectNotFoundException, ObjectAlreadyExistsException {
        orderService.saveOrder(orderDTO);
    }

    @GetMapping(value = "/{id}")
    public OrderDTO getOrderById(@PathVariable Integer id) throws ObjectNotFoundException {
        return orderService.getOrderById(id);
    }
    @GetMapping(value = "/available")
    public List<RoomDTO> getRoomsWillBeAvailableAt(@PathVariable LocalDate date) throws ObjectNotFoundException {
        return orderService.getRoomsWillBeAvailableAt(date);
    }
    @GetMapping
    public List<OrderDTO> getAllOrders() {
    return orderService.getAllOrders();
    }
    @GetMapping(value = "/price")
    private long getPriceForOrder(@PathVariable Integer id) throws ObjectNotFoundException {
        return orderService.getPriceForOrder(id);
    }
    @GetMapping(value = "/lastThree")
    public List<OrderDTO> getThreeLastGuests()
    {
        return orderService.getThreeLastGuests();
    }
    @GetMapping(value = "/delete")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGuestFromRoom(@PathVariable Integer orderId) throws ObjectNotFoundException {
        orderService.deleteGuestFromRoom(orderId);
    }
}