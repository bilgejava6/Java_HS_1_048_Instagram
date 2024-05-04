package com.muhammet.criteriaQuery;

import com.muhammet.entity.Comment;
import com.muhammet.entity.Post;
import com.muhammet.view.VwComment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    /**
     * select * from tblcomment -> ilgili entity içinde ki tüm alanlar gelir
     * Ancak bazen tek bir alan almak ihtiyacı olacaktır böyle durumlarda
     * select comment  from tblcomment
     */
    public List<String> selectOneColumn(){
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Comment> root = criteriaQuery.from(Comment.class);
        criteriaQuery.select(root.get("comment"));
//        List<String> result = em.createQuery(criteriaQuery).getResultList();
//        return result;
        return  em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * select comment from tblcomment where postid=?
     */
    public List<String> selectOneColumnByPostId(Long postId){
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Comment> root = criteriaQuery.from(Comment.class);
        // select comment
        criteriaQuery.select(root.get("comment"));
        // where postid = ?
        criteriaQuery.where(criteriaBuilder.equal(root.get("postid"),postId));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * select * from tblcomment where id = ?
     * -> Bu sorgu ya tek bir değer döner ya da boş döner
     */
    public Optional<Comment> findById(Long id){
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> root = criteriaQuery.from(Comment.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));
        Comment result = em.createQuery(criteriaQuery).getSingleResult();
        if(result!=null)
            return Optional.of(result);
        else
            return Optional.empty();
//        return em.createQuery(criteriaQuery).getSingleResult();
    }

    /**
     *
     * select userid, comment from tblcomment where postid = ?
     * Örnek Result:
     *  userid - comment
     * -> 5, "burası güzelmiş"
     * -> 43, "ooo bizsiz gezmelere gidilmiş"
     * -> 356, "bekle geliyorum."
     * -------------------------------
     * ArrayList<String> X
     * Object[] -> {5, "burası güzelmiş"}
     */
    public List<Object[]> selecyManyColumn(Long postId){
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Comment> root = criteriaQuery.from(Comment.class);
        // select userid, comment from
        /**
         * opsiyon - 1
         */
        //criteriaQuery.select(criteriaBuilder.array(root.get("userid"),root.get("comment")));
        /**
         * opsiyon - 2
         */
        Path<Long> userid = root.get("userid");
        Path<String> comment = root.get("comment");
        criteriaQuery.select(criteriaBuilder.array(userid,comment));
        criteriaQuery.where(criteriaBuilder.equal(root.get("postid"),postId));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * select * from tblcomment where postid= ? and userid>60 and comment like '%ankara%'
     */
     public List<Comment> findAllByPostIdAndUserIdGteAndCommentLike(Long postId, Long userId,String comment){
         CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
         Root<Comment> root = criteriaQuery.from(Comment.class);
         criteriaQuery.select(root);
         /**
          * where postid = ?
          * -> postid = root.get("postid")
          * -> ? = postId
          */
         Predicate predicatePostId = criteriaBuilder.equal(root.get("postid"),postId);
         Predicate predicateUserId = criteriaBuilder.greaterThan(root.get("userid"),userId);
         Predicate predicateCommentLike = criteriaBuilder.like(root.get("comment"),"%"+comment+"%");

         criteriaQuery.where(criteriaBuilder.and(predicatePostId,predicateUserId,predicateCommentLike));

         return em.createQuery(criteriaQuery).getResultList();
     }


    /**
     *
     * Native Query - Hibernate üzerinden direkt SQL komutlarını çalıştırabildiğiniz yapıdır.
     *
     */
    public List<Comment> findAllNativeSQL(){
        List<Comment> result = em.createNativeQuery("select * from tblcomment", Comment.class).getResultList();
        return result;
    }
    /**
     * select comment from tblcomment
     */
    public List<String> getOneColumnNativeSQL(){
        return em.createNativeQuery("select comment from tblcomment",String.class).getResultList();
    }

    /**
     * select userid, comment from tblcomment
     *
     */
    public List<VwComment> getViewNativeSQL(){
        return em.createNativeQuery("select id,userid,comment from tblcomment", VwComment.class).getResultList();
    }

    /**
     *
     * Named Query ile çalışmak
     *
     */

    public List<Post> findAllNamedQuery(){
        return em.createNamedQuery("Post.findAll", Post.class).getResultList();
    }

    public BigDecimal countPostSize(){
        return em.createNamedQuery("Post.countAll", BigDecimal.class).getSingleResult();
//        TypedQuery<BigDecimal> typedQuery = em.createNamedQuery("Post.countAll", BigDecimal.class);
//        return typedQuery.getSingleResult();
    }

    public List<Post> findAllByUserId(Long userId){
       TypedQuery<Post> typedQuery =  em.createNamedQuery("Post.findAllByUserId", Post.class);
       typedQuery.setParameter("userid",userId);
       return typedQuery.getResultList();
    }
}
