package com.muhammet;

import com.muhammet.entity.Comment;
import com.muhammet.entity.Like;
import com.muhammet.repository.CommentRespository;
import com.muhammet.repository.LikeRespository;

public class RunnerRepos {
    public static void main(String[] args) {
//        CommentRespository commentRespository = new CommentRespository();
//        commentRespository.save(Comment.builder()
//                        .comment("Yeni bir yorum yaptım")
//                        .date(System.currentTimeMillis())
//                        .postid(1L)
//                        .userid(2L)
//                .build());
        LikeRespository likeRespository = new LikeRespository();
//        likeRespository.save(Like.builder()
//                        .date(System.currentTimeMillis())
//                        .islike(true)
//                        .postid(2L)
//                        .repeated(false)
//                        .userid(3L)
//                .build());
    }
}
