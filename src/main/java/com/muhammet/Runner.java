package com.muhammet;

import com.muhammet.entity.User;
import com.muhammet.utility.enums.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Runner {
    public static void main(String[] args) {
        User user = User.builder()
                .email("muhammet@gmail.com")
                .password("123456")
                .username("bahar")
                .isActive(true)
                .phone("0 555 999 8877")
                .state(State.BLOKED)
                .build();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("insta");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        /**
         * persist -> ekleme ve güncelleme
         * id null ise ekleme işlemi yapar
         * id null değil ise var olan kayıt güncellenir, ancak ilgili id daha önce kullanılmamış ise ekleme yapar.
         *
         * remove(Entity) -> silme işlemi yapar.
         */
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        emf.close();


    }
}
