package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.service.GuestService;
import com.senlainc.gitcourses.kashko.raman.dto.GuestDTO;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {


    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }


    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public void saveGuest(@RequestBody GuestDTO guest) throws ObjectAlreadyExistsException {
        guestService.saveGuest(guest);
    }
    @GetMapping(value ="/{id}")
    public GuestDTO getGuest(@PathVariable Integer id) throws ObjectNotFoundException {
        return guestService.getGuest(id);
    }

    @GetMapping
    public List<GuestDTO> getAllGuests() throws ObjectNotFoundException {
        return guestService.getAllGuests();
    }
    @GetMapping(value = "/sortalphabet")
    public List<GuestDTO> guestsSortedByAlphabet() {
        return guestService.guestsSortedByAlphabet();
    }

    @GetMapping(value = "/count")
    public long countGuests(){
        return guestService.countGuests();
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeInfoAboutGuest(@RequestBody GuestDTO guestDTO) {
         guestService.changeInfoAboutGuest(guestDTO);
    }
}
