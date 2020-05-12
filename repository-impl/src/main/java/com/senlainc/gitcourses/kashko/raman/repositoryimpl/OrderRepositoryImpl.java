package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.OrderRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Order;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class OrderRepositoryImpl extends AbstractDAO<Order, Integer> implements OrderRepository {
    private Integer maxSize;

    public OrderRepositoryImpl() throws IOException {
    }

    @Override
    public List<Order> getThreeLast() {
        return getEntityManager().createQuery("SELECT o FROM Order o ORDER BY id desc",
                Order.class).setMaxResults(3).getResultList();
    }


    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }

}
