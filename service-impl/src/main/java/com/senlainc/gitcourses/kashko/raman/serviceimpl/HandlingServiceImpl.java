package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.HandlingRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.HandlingService;
import com.senlainc.gitcourses.kashko.raman.dto.HandlingDTO;
import com.senlainc.gitcourses.kashko.raman.entity.Handling;
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
public class HandlingServiceImpl implements HandlingService {
    private static final Logger LOGGER = Logger.getLogger(HandlingServiceImpl.class);

    private final HandlingRepository handlingRepository;
    private ModelMapper modelMapper;

    @Autowired
    public HandlingServiceImpl(HandlingRepository handlingRepository, ModelMapper modelMapper) {
        this.handlingRepository = handlingRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void saveHandling(HandlingDTO handlingDTO) {
        try {
            Handling handling = modelMapper.map(handlingDTO, Handling.class);
            handlingRepository.save(handling);
        } catch (DaoException e) {
            LOGGER.log(Level.WARN, "Dao exc");
        }
    }

    @Override
    public List<HandlingDTO> getAllHandlings() {
        try {
            return handlingRepository.getAll().stream()
                    .map(x -> modelMapper.map(x, HandlingDTO.class)).
                            collect(Collectors.toList());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
            return null;
        }
    }

    @Override
    public HandlingDTO getById(Integer id) {
        try {
            Handling handling = handlingRepository.getById(id);
            return modelMapper.map(handling, HandlingDTO.class);
        } catch (DaoException | ObjectNotFoundException e) {
            LOGGER.log(Level.ERROR, "Can't get", e);
            return null;
        }
    }


}
