package com.nooglers.dao;

import com.nooglers.domains.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDAO<T extends BaseEntity, ID extends Serializable> {
    private final EntityManagerFactory emf;
    protected final EntityManager entityManager;
    private final Class<T> persistenceClass;

    @SuppressWarnings("unchecked")
    protected BaseDAO() {
        this.emf = Persistence.createEntityManagerFactory("persistence_unit");
        this.entityManager = emf.createEntityManager();
        this.persistenceClass = (Class<T>) (((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0]);
    }

    public T save(T t) {
        begin();
        entityManager.persist(t);
        commit();
        return t;
    }

    public T findById(ID id) {
        return entityManager.find(persistenceClass, id);
    }

    public boolean update(T t) {
        begin();
        entityManager.merge(t);
        commit();
        return true;
    }

    public boolean delete(T t) {
        entityManager.remove(t);
        return true;
    }

    public boolean deleteById(ID id) {
        return entityManager.createQuery("delete from " + persistenceClass.getSimpleName() + " t where t.id = :id")
                .setParameter("id", id)
                .executeUpdate() == 0;
    }

    protected void begin() {
        entityManager.getTransaction().begin();
    }

    protected void commit() {
        entityManager.getTransaction().commit();
    }
}