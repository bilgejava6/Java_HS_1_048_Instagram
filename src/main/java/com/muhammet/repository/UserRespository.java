package com.muhammet.repository;

import com.muhammet.entity.User;
import com.muhammet.utility.Repository;

public class UserRespository extends Repository<User,Long> {
    public UserRespository(){
        super(new User());
    }
}
