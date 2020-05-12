package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.GuestRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.GuestService;
import com.senlainc.gitcourses.kashko.raman.dto.GuestDTO;
import com.senlainc.gitcourses.kashko.raman.entity.Guest;
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
public class GuestServiceImpl implements GuestService {

    private static final Logger LOGGER = Logger.getLogger(GuestServiceImpl.class);


    private final GuestRepository guestRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository, ModelMapper modelMapper) {
        this.guestRepository = guestRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveGuest(GuestDTO guestDTO) {
        try {
            Guest guest = modelMapper.map(guestDTO, Guest.class);
            guestRepository.save(guest);
        } catch (DaoException e) {
            LOGGER.log(Level.WARN, "Can't save");
        }

    }

    @Override
    public GuestDTO getGuest(Integer id) {
        try {
            Guest guest = guestRepository.getById(id);
            return modelMapper.map(guest, GuestDTO.class);
        } catch (DaoException | ObjectNotFoundException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
            return null;
        }
    }

    @Override
    public List<GuestDTO> getAllGuests() {
        try {

            return guestRepository.getAll().stream()
                    .map(x -> modelMapper.map(x, GuestDTO.class))
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
            return null;
        }
    }

    @Override
    public List<GuestDTO> guestsSortedByAlphabet() {
        return guestRepository.guestsSortedByAlphabet().stream()
                .map(x -> modelMapper.map(x, GuestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public long countGuests() {
        return guestRepository.getCountGuests();
    }

    @Override
    public void changeInfoAboutGuest(GuestDTO guestDTO) {
        try {
            Guest guest = modelMapper.map(guestDTO, Guest.class);
            guestRepository.update(guest);
        } catch (DaoException e) {
            LOGGER.log(Level.WARN, "Can't change info");
        }

    }


}
