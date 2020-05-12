package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.RoomRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class RoomRepositoryImpl extends AbstractDAO<Room, Integer> implements RoomRepository {

    @Override
    public List<Room> getFreeRoomList() {
        return getEntityManager().createQuery("SELECT r FROM Room r where status = 'FREE'",
                Room.class).getResultList();
    }

    @Override
    public List<Room> getRoomListSort(String sortBy) {
        return getEntityManager().createQuery("SELECT r FROM Room r ORDER BY :sortBy asc",
                Room.class).setParameter("sortBy", sortBy).getResultList();
    }

    @Override
    public List<Room> getFreeRoomListSort(String sortBy) {
        return getEntityManager().createQuery("SELECT r FROM Room r where status = 'Free' ORDER BY :sortBy asc",
                Room.class).setParameter("sortBy", sortBy).getResultList();
    }

    @Override
    public long countFreeRooms() {
        long countFreeRooms = 0;
        Query query = getEntityManager().createQuery("SELECT count(*) from Room where status = 'FREE'");
        countFreeRooms = (long) query.getSingleResult();
        return countFreeRooms;
    }


    @Override
    public Class<Room> getEntityClass() {
        return Room.class;
    }


}
