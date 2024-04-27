package com.muhammet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbllike")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long postid;
    Long userid;
    Long date;
    boolean repeated; //true
    boolean islike; //false
    /**
     * repeated = true ve islike = false -> önceden like yapmış sonra like kaldırmış.
     * repeated = false ve islke = false -> hiç like yapmamış
     *
     */
}
