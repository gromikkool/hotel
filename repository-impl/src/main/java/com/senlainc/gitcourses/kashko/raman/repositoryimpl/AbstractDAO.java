package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.GenericDAO;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDAO<T, PK extends Serializable> implements GenericDAO<T, PK> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    public abstract Class<T> getEntityClass();

    public EntityManager getEntityManager() {
        return entityManager;
    }


    @Override
    public T getById(PK id) throws ObjectNotFoundException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(getEntityClass());
        Root<T> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.where(cb.equal(root.get("id"), id));
        List<T> result = entityManager.createQuery(criteriaQuery).getResultList();
        if (!result.isEmpty()) {
            return result.iterator().next();
        } else {
            throw new ObjectNotFoundException("no id");
        }
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> guestCriteriaQuery = cb.createQuery(getEntityClass());
        Root<T> root = guestCriteriaQuery.from(getEntityClass());
        guestCriteriaQuery.select(root);
        List<T> ss = entityManager.createQuery(guestCriteriaQuery).getResultList();
        return entityManager.createQuery(guestCriteriaQuery).getResultList();
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
        LOGGER.info("Succesfully update");
    }

    public void delete(int id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> delete = cb.
                createCriteriaDelete(getEntityClass());
        Root root = delete.from(getEntityClass());
        delete.where(cb.equal(root.get("id"), id));
        entityManager.createQuery(delete).executeUpdate();
    }
}

