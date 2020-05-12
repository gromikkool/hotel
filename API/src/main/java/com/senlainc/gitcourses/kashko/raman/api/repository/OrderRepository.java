package com.senlainc.gitcourses.kashko.raman.api.repository;

import com.senlainc.gitcourses.kashko.raman.entity.Order;

import java.util.List;

public interface OrderRepository extends GenericDAO<Order, Integer> {

    List<Order> getThreeLast();
}
