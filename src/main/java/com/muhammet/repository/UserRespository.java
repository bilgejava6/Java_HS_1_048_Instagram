package com.muhammet.repository;

import com.muhammet.entity.User;
import com.muhammet.utility.Repository;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class UserRespository extends Repository<User,Long> {
    public UserRespository(){
        super(new User());
    }

    public boolean isUser(String userName){
        boolean result = (boolean)getEm().createNativeQuery("select  count(*)>0 from tbluser where user_name='"+userName+"'").getSingleResult();
        return result;
    }

    public boolean isExist(String userName){
        openEnitityManager();
        TypedQuery<Boolean> typedQuery = getEm().createNamedQuery("User.isExist", Boolean.class);
        typedQuery.setParameter("userName",userName);
        return typedQuery.getSingleResult();
    }

    public Optional<User> findByUsernameAndPassword(String userName, String password){
        openEnitityManager();
        TypedQuery<User> typedQuery = getEm().createNamedQuery("User.findByUsernameAndPassword", User.class);
        typedQuery.setParameter("userName",userName);
        typedQuery.setParameter("password",password);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }

}
