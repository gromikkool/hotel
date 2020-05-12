package com.senlainc.gitcourses.kashko.raman.api.repository;

import com.senlainc.gitcourses.kashko.raman.entity.Guest;

import java.util.List;


public interface GuestRepository extends GenericDAO<Guest, Integer> {
    List<Guest> guestsSortedByAlphabet();

    long getCountGuests();

}
