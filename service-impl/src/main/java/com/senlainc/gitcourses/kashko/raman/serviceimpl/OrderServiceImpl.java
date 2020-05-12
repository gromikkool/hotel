package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.OrderRepository;
import com.senlainc.gitcourses.kashko.raman.api.repository.RoomRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.OrderService;
import com.senlainc.gitcourses.kashko.raman.dto.OrderDTO;
import com.senlainc.gitcourses.kashko.raman.dto.RoomDTO;
import com.senlainc.gitcourses.kashko.raman.entity.Order;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    private final RoomRepository roomRepository;

    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, RoomRepository roomRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        try {
            Order order = modelMapper.map(orderDTO, Order.class);
            orderRepository.save(order);
        } catch (DaoException e) {
            LOGGER.log(Level.WARN, "can't save order");
        }

    }

    @Override
    public OrderDTO getOrderById(Integer id) {
        try {
            Order order = orderRepository.getById(id);
            return modelMapper.map(order, OrderDTO.class);
        } catch (DaoException | ObjectNotFoundException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
            return null;
        }
    }

    @Override
    public List<RoomDTO> getRoomsWillBeAvailableAt(LocalDate date) {
        List<RoomDTO> freeRooms = roomRepository.getFreeRoomList().stream()
                .map(x -> modelMapper.map(x, RoomDTO.class)).
                        collect(Collectors.toList());
        List<OrderDTO> orderList = getAllOrders().stream()
                .filter(order -> order.getDateDeparture()
                        .isBefore(date))
                .collect(Collectors.toList());
        for (OrderDTO order : orderList) {
            freeRooms.add(modelMapper.map(order.getRoom(), RoomDTO.class));
        }
        return freeRooms;

    }

    @Override
    public List<OrderDTO> getAllOrders() {
        try {
            return orderRepository.getAll().stream()
                    .map(x -> modelMapper.map(x, OrderDTO.class))
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
            return null;
        }
    }

    private int getPriceForOneDay(Integer id) throws ObjectNotFoundException {
        Room room = null;
        try {
            room = roomRepository.getById(orderRepository.getById(id).getRoom().getId());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
        }
        assert room != null;
        return room.getPricePerDay();
    }


    @Override
    public long getPriceForOrder(Integer id) throws ObjectNotFoundException {
        return getPriceForOneDay(id) * getCountOFDaysOfGuest(id) + getPriceForServices(id);
    }

    private long getCountOFDaysOfGuest(Integer id) throws ObjectNotFoundException {
        Order order = null;
        try {
            order = orderRepository.getById(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
        }
        assert order != null;
        return ChronoUnit.DAYS.between(order.getDateArrival(), order.getDateDeparture());
    }

    @Override
    public List<OrderDTO> getThreeLastGuests() {
        return orderRepository.getThreeLast().stream()
                .map(x -> modelMapper.map(x, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public int getPriceForServices(Integer id) throws ObjectNotFoundException {
        Order order = null;
        Integer price = 0;
        try {
            order = orderRepository.getById(id);
            for (int i = 0; i < order.getHandling().size(); i++) {
                price += orderRepository.getById(id).getHandling().get(i).getPrice();
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
        }
        return price;
    }


    @Override
    public void deleteGuestFromRoom(Integer orderId) throws ObjectNotFoundException {
        OrderDTO order = getOrderById(orderId);
        order.setGuest(null);
        Room room;
        try {
            room = roomRepository.getById(order.getRoom().getId());
            room.setStatus(RoomStatus.FREE);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
        }


    }

}
