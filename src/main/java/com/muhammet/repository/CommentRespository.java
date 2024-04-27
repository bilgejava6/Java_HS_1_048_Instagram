package com.muhammet.repository;

import com.muhammet.entity.Comment;
import com.muhammet.utility.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class CommentRespository implements ICrud<Comment, Long> {
    private final EntityManagerFactory emf;
    private EntityManager em;

    public CommentRespository(){
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

    private void Rollback(){
        em.getTransaction().rollback();
        em.close();
    }

    @Override
    public Comment save(Comment entity) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("insta");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(entity);
//        em.getTransaction().commit(); // id bilgisi entity içerisine eklenir.
//        em.close();
//        emf.close();
        openSession();
        em.persist(entity);
        closeSession();
        return entity;
    }

    @Override
    public Iterable<Comment> saveAll(Iterable<Comment> entities) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("insta");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        entities.forEach(entity->{
//            em.persist(entity);
//        });
//        em.getTransaction().commit();
//        em.close();
//        emf.close();
        try{
            openSession();
            entities.forEach(em::persist);
            closeSession();
        }catch (Exception ex){
            Rollback();
        }
        return entities;
    }

    @Override
    public Optional<Comment> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public List<Comment> findAllByFromColumnAndValue(String columnName, Object value) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<Comment> findAllByEntity(Comment entity) {
        return null;
    }
}
