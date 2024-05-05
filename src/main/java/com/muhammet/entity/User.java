package com.muhammet.entity;

import com.muhammet.utility.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQueries({
        @NamedQuery(name = "User.isExist",query = "select count(u)>0 from User u where u.username= :userName"),
        @NamedQuery(name = "User.findByUsernameAndPassword",
                    query = "select u from User u where u.username= :userName and u.password= :password")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbluser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    @Id
//    /**
//     * Kullanılan DB de hibernate için bir squence oluşturur. ve id nin bu sequence ile çalışması sağlanır.
//     * name -> Hibernate için tanımlanan isimdir. GeneretedValue için seçici isimdir.
//     */
//    @SequenceGenerator(name = "sq_user_id", sequenceName = "sq_user_id", initialValue = 1000, allocationSize = 20)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user_id")
//    Long id2;

    @Column(name="user_name",
            unique = true,
            nullable = false,
            length = 64,
            updatable = false)
    String username;
    @Column(nullable = false,length = 128)
    String password;
    @Column(length = 64)
    String email;
    @Column(length = 20)
    String phone;
    Boolean isActive;
    /**
     * Enumlar şu şekilde çalışırlar
     * String -> Integer
     * ACTIVE -> 1
     * Eğer enum için bir kullanımı tipi belirmeziseniz Integer olarak
     * DB ye kayıt yapar.
     * yukarıda ki örnek için
     * EnumType.ORDINAL kullanırsan -> state 1
     * EnumType.STRING kullanırsan -> state ACTIVE
     */
    @Enumerated(EnumType.STRING)
    State state;

}
