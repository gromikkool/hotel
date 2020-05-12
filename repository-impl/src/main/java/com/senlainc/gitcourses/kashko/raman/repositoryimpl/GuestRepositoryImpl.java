package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.GuestRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Guest;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class GuestRepositoryImpl extends AbstractDAO<Guest, Integer> implements GuestRepository {

    @Override
    public Class<Guest> getEntityClass() {
        return Guest.class;
    }


    public List<Guest> guestsSortedByAlphabet() {
        return getEntityManager().createQuery("SELECT g FROM Guest g ORDER BY clientName asc",
                Guest.class).getResultList();
    }

    @Override
    public long getCountGuests() {
        long countGuests = 0;
        Query query = getEntityManager().createQuery("SELECT count(*) from Guest");
        countGuests = (long) query.getSingleResult();
        return countGuests;
    }

}
