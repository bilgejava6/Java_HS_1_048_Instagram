package com.muhammet;

import com.muhammet.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Runner_List {
    public static void main(String[] args) {
        /**
         * JDBC -> connection, statement(SQL), resultset
         * HIBERNATE
         * 1- connection
         * 2- SQL -> criteria
         * 3- result
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("insta");
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        /**
         * select * from tbluser
         * 1- criteria sorgusu oluşturmak için gerekli olan kolon bilgilerini ve tablo
         * bilgisini çekmek için kullanacağımız entity sınıfını blelirtiyoruz.
         */
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        /**
         * 2- select *  ya da select id,ad,adres
         * burada select ile hangi alanları okunacapunu belirtiyoruz
         */
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        /**
         * 2.1- Eğer sorguda kısıtlamalara gidilmek isteniyor ise burada where kullanılarak
         * kısıtlar yazılır.
         * Burada kısıtlar predicate olarak tanımlana bilir ve birden fazla kısıt girilebilir
         * yada tek bir kısıt var ise direkt yazılabilir.
         * where ad = 'bahar'
         */
        //criteriaQuery.where(criteriaBuilder.equal(root.get("username"),"bahar"));
        criteriaQuery.where(criteriaBuilder.like(root.get("username"),"%e%"));
        /**
         * 3- ilgili sorgu tanımlandığına göre bu sorguyu kullanarak listeyi çekiyoruz.
         */
        List<User> userLists = em.createQuery(criteriaQuery).getResultList();

        em.close();
        emf.close();
        System.out.println("Kullanıcı Listesi");
        System.out.println("---------------------------");
        userLists.forEach(System.out::println);
    }
}
