package com.muhammet.entity;

import jakarta.persistence.*;
import lombok.*;


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
