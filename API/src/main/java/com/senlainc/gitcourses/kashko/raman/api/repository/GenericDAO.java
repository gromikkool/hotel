package com.senlainc.gitcourses.kashko.raman.api.repository;

import com.senlainc.gitcourses.kashko.raman.exceptions.DaoException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, PK extends Serializable> {
    T getById(PK id) throws DaoException, ObjectNotFoundException;

    List<T> getAll() throws DaoException;

    T save(T t) throws DaoException;

    void update(T t) throws DaoException;

    void delete(int id) throws DaoException;
}
