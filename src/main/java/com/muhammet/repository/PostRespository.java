package com.muhammet.repository;

import com.muhammet.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;

public class PostRespository {

    private final EntityManagerFactory emf;
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;

    public PostRespository(){
        this.emf = Persistence.createEntityManagerFactory("insta");
        this.em = emf.createEntityManager();
        this.criteriaBuilder = em.getCriteriaBuilder();
    }

    private void openSession(){
        this.em = emf.createEntityManager();
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

    public Post save(Post post){
        openSession();
        em.persist(post);
        closeSession();
        return post;
    }



}
