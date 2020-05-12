package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.service.HandlingService;
import com.senlainc.gitcourses.kashko.raman.dto.GuestDTO;
import com.senlainc.gitcourses.kashko.raman.dto.HandlingDTO;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/handling")
public class HandlingController {

    private final HandlingService handlingService;

    @Autowired
    public HandlingController(HandlingService handlingService) {
        this.handlingService = handlingService;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public void saveHandling(@RequestBody HandlingDTO handlingDTO) throws ObjectAlreadyExistsException {
        handlingService.saveHandling(handlingDTO);
    }

    @GetMapping
    public List<HandlingDTO> getAllHandlings() {
       return handlingService.getAllHandlings();
    }
    @GetMapping(value = "/{id}")
    public HandlingDTO getById(@PathVariable Integer id) throws ObjectNotFoundException {
        return handlingService.getById(id);
    }
}
