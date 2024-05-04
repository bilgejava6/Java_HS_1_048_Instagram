package com.muhammet.service;

import com.muhammet.entity.Post;
import com.muhammet.entity.User;
import com.muhammet.repository.PostRespository;
import com.muhammet.repository.UserRespository;

import java.util.Optional;

public class PostService {
    private final PostRespository postRespository;
    private final UserRespository userRespository;
    public PostService(){
        this.postRespository = new PostRespository();
        this.userRespository = new UserRespository();
    }

    public void addNewPost(Long userId, String comment, String imageUrl,String location){
        Optional<User> user = userRespository.findById(userId);
        if(user.isEmpty()) return; // Eğer user yok ise işlemi bitir.
        postRespository.save(Post.builder()
                        .comment(comment)
                        .commentcount(0)
                        .imageurl(imageUrl)
                        .likes(0)
                        .location(location)
                        .shareddate(System.currentTimeMillis())
                        .userid(userId)
                .build());
    }

}
