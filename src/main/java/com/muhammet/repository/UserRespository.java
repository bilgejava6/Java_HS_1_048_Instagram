package com.muhammet.repository;

import com.muhammet.entity.User;
import com.muhammet.utility.Repository;
import jakarta.persistence.TypedQuery;

public class UserRespository extends Repository<User,Long> {
    public UserRespository(){
        super(new User());
    }

    public boolean isUser(String userName){
        boolean result = (boolean)getEm().createNativeQuery("select  count(*)>0 from tbluser where user_name='"+userName+"'").getSingleResult();
        return result;
    }

    private boolean isExist(String userName){
        TypedQuery<Boolean> typedQuery = getEm().createNamedQuery("User.isExist", Boolean.class);
        typedQuery.setParameter("userName",userName);
        return typedQuery.getSingleResult();
    }

}
