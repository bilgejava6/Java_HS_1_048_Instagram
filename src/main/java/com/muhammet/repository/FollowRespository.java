package com.muhammet.repository;

import com.muhammet.entity.Follow;
import com.muhammet.utility.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class FollowRespository  implements ICrud<Follow, Long> {

    private final EntityManagerFactory emf;
    private EntityManager em;

    public FollowRespository(){
        emf = Persistence.createEntityManagerFactory("insta");
    }

    private void openSession(){
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    private void closeSession(){
        em.getTransaction().commit();
        em.close();
    }

    private void RollBack(){
        em.getTransaction().rollback();
        em.close();
    }
    @Override
    public Follow save(Follow entity) {
        openSession();
        em.persist(entity);
        closeSession();
        return entity;
    }

    @Override
    public Iterable<Follow> saveAll(Iterable<Follow> entities) {
        return null;
    }

    @Override
    public Optional<Follow> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Follow> findAll() {
        return null;
    }

    @Override
    public List<Follow> findAllByFromColumnAndValue(String columnName, Object value) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<Follow> findAllByEntity(Follow entity) {
        return null;
    }
}
