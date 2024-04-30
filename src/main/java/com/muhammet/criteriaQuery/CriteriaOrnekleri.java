package com.muhammet.criteriaQuery;

import com.muhammet.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CriteriaOrnekleri {

    private EntityManagerFactory emf;
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;
    public CriteriaOrnekleri(){
        emf = Persistence.createEntityManagerFactory("insta");
        em = emf.createEntityManager();
        criteriaBuilder = em.getCriteriaBuilder();
    }

    /**
     * Select * from tblcomment
     */
    public List<Comment> findAll(){
        /**
         * mutlaka bir entity sınıfı verilir. Burada reflection kullanılarak sınıf analiz edilir.
         */
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        /**
         * Select işlemi için seçilecek alanları belirleyebilmek önemlidir. Elle yazarken alanları
         * biz belirleriz, ancak bu işlem belirsiz sınıflar üzerinden yapılırken Generic Type olarak
         * alınır ve Reflection ile çözülür.
          */
        Root<Comment> root = criteriaQuery.from(Comment.class);
        /**
         * select * from
         */
        criteriaQuery.select(root);
        /**
         * oluşturduğumuz sorguyu çalıştırıyoruz.
         */
        return em.createQuery(criteriaQuery).getResultList();
    }

}
