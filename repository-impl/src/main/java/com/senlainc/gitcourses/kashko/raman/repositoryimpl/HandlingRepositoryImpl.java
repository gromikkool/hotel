package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.HandlingRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Handling;
import org.springframework.stereotype.Repository;

@Repository
public class HandlingRepositoryImpl extends AbstractDAO<Handling, Integer> implements HandlingRepository {


    @Override
    public Class<Handling> getEntityClass() {
        return Handling.class;
    }


}
