package com.muhammet.utility;

import java.util.List;
import java.util.Optional;

public interface ICrud<T,ID> {

    T save(T entity);
    Iterable<T> saveAll(Iterable<T> entities); // List, Set
    Optional<T> findById(ID id); // select * from tbluser where id = 5000
    boolean existsById(ID id);
    List<T> findAll();
    /**
     * bir post un yorum listesini bulmak istiyorum.
     * select * from tblcomment where postid = ?
     */
    List<T> findAllByFromColumnAndValue(String columnName, Object value);
    void deleteById(ID id);
    List<T> findAllByEntity(T entity);
}
