package com.muhammet;

import com.muhammet.entity.User;
import com.muhammet.repository.UserRespository;

public class Runner_RepositoryTest {
    public static void main(String[] args) {
        User user = User.builder()
                .username("at")
                .build();
        UserRespository userRespository = new UserRespository();
        userRespository.findAllByEntity(user).forEach(System.out::println);
    }
}
