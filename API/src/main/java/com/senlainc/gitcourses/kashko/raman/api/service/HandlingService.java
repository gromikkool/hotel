package com.senlainc.gitcourses.kashko.raman.api.service;

import com.senlainc.gitcourses.kashko.raman.dto.HandlingDTO;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;

import java.util.List;

public interface HandlingService {

    void saveHandling(HandlingDTO handling) throws ObjectAlreadyExistsException;

    List<HandlingDTO> getAllHandlings();

    HandlingDTO getById(Integer id) throws ObjectNotFoundException;
}
