package com.muhammet.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * Named Query
 * önbellekleme yapabilirler, bu nedenle aynı sorgu tekrar çağırıldığında veriler önbellekten çekilir.
 * Named query yazmak için 3 farklı dil kullanabilirsiniz.
 * HQL -> Hibernate Query Language
 * JPQL -> Jakarta Persistence Query Language
 * NativeSQL
 * ---------- SOrgu Yazma Şekilleri --------
 * NativeSQL    -> select * from tblpost
 * JPQL         -> select p from Post p (Post p -> alians kullanılır. yani Post kısaca p ile gösterilir.)
 * HQL          -> from Post
 * ------------------------
 * Named Query ler ilgili sınıfın üzerine bir anotasyon yardımı ile yazılırlar.
 * Eğer tek bir Query kullanılacak ise tek tek yazılabilir, birden fazla sorgu yazılacak ise
 * array şeklinde query ler eklenebilir.
 * Integer[] {12,2113,3423,434,5}
 */
@NamedQueries({
        /**
         * NamedQuery lere isimlendirme yaparken şu formatta yazmak uygundur. [Entity_Name].[Query_Name]
         */
     @NamedQuery(name = "Post.findAll", query = "select p from Post p"),
     @NamedQuery(name = "Post.countAll",query = "select count(p) from Post p"),
        /**
         * DİKKAT!!!!! Eğer NamedQuery içerisine bir değer girmeniz gerekiyor ise bunu
         * eklemek için değişken taımlamalısınız. NamedQuery içerisine değişken tanımlamak
         * için  ":[DEĞİŞKEN_ADI]" şeklinde kullanmalısınız.
         */
     @NamedQuery(name = "Post.findAllByUserId",query = "select p from Post p where p.userid = :userid")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tblpost")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userid;
    Long shareddate;
    String comment;
    String imageurl;
    Integer likes;
    Integer commentcount;
    String location;
}
