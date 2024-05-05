package com.muhammet.service;

import com.muhammet.entity.User;
import com.muhammet.repository.UserRespository;
import com.muhammet.utility.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//        List<User> findUser =  userRespository.findAllByFromColumnAndValue("username",userName);
        Response<Boolean> response = new Response<>();

        if(userRespository.isExist(userName)){ // kullanıcı adı başka birisi tarafından alınmış.
            response.setData(false);
            response.setMessage("Bu kullanıcı adı daha önce alınmıştır. Lütfen başka bir değer giriniz.");
            response.setStatusCode(400);
            return response;
        }

        userRespository.save(User.builder()
                        .username(userName)
                        .email(email)
                        .password(password)
                        .phone("")
                        .isActive(true)
                .build());
        response.setData(true);
        response.setStatusCode(200);
        response.setMessage("Kullanıcı başarı ile kayıt edilmiştir.");
        return response;
    }

    public Response<User> login(String userName, String password) {
        Optional<User> userOptional = userRespository.findByUsernameAndPassword(userName,password);
        Response<User> response = new Response<>();
        if(userOptional.isEmpty()){
            response.setStatusCode(400);
            response.setMessage("Kullanıcı adı ya da şifre hatalıdır. Lütfen tekrar deneyiniz.");
            response.setData(null);
            return response;
        }

        response.setStatusCode(200);
        response.setMessage("Ok.");
        response.setData(userOptional.get());
        return response;
    }
}
