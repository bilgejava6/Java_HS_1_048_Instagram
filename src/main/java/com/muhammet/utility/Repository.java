package com.muhammet.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class Repository<T,ID> implements ICrud<T,ID>{
    private final EntityManagerFactory emf;
    private EntityManager em;
//    private T t;
    public Repository(){
        emf = Persistence.createEntityManagerFactory("insta");
//        this.t = entity;
    }

    private void openSession(){
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    private void closeSession(){
        em.getTransaction().commit();
        em.close();
    }
    private void rollBack(){
        em.getTransaction().rollback();
        em.close();
    }

    @Override
    public T save(T entity) {
        openSession();
        em.persist(entity);
        closeSession();
        return entity;
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        try{
            openSession();
            entities.forEach(em::persist);
            closeSession();
        }catch (Exception exception){
            rollBack();
        }
        return entities;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(ID id) {
        return false;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findAllByFromColumnAndValue(String columnName, Object value) {
        return null;
    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public List<T> findAllByEntity(T entity) {
        return null;
    }
}
