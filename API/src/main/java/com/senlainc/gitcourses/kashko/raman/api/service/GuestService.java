package com.senlainc.gitcourses.kashko.raman.api.service;

import com.senlainc.gitcourses.kashko.raman.dto.GuestDTO;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;

import java.util.List;

public interface GuestService {
    void saveGuest(GuestDTO guest) throws ObjectAlreadyExistsException;

    GuestDTO getGuest(Integer id) throws ObjectNotFoundException;

    List<GuestDTO> getAllGuests() throws ObjectNotFoundException;

    List<GuestDTO> guestsSortedByAlphabet();

    long countGuests();

    void changeInfoAboutGuest(GuestDTO guest);
}
