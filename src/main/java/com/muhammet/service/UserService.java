package com.muhammet.service;

import com.muhammet.entity.User;
import com.muhammet.repository.UserRespository;
import com.muhammet.utility.Response;

import java.util.List;

public class UserService {
    private final UserRespository userRespository;
    public UserService(){
        this.userRespository = new UserRespository();
    }
    public Response<Boolean> register(String userName, String email, String password) {
        /**
         * 1- userName, daha önce bir kişi aynı ismi almış ise sorun olmalı.
         * 2- email, daha önce başka bir kişi aynı email ile üye olmuş ve doğrulamış ise sorun olmalı.
         * 3- tanımsız beklenmeyen bir hata olabilir.
         */
        List<User> findUser =  userRespository.findAllByFromColumnAndValue("username",userName);

        userRespository.save(User.builder()
                        .username(userName)
                        .email(email)
                        .password(password)
                        .phone("")
                        .isActive(true)
                .build());

    }
}
