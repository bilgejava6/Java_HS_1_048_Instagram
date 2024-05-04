package com.muhammet;

import com.muhammet.entity.Comment;

import java.util.Optional;

public class RunneOptional {
    public static void main(String[] args) {
//        Comment comment = findById(2L);
//        if(comment != null)
//            System.out.println("yorum..: "+ comment.getComment());
        findById(3L).ifPresent(comment ->
                System.out.println(comment.getComment()));
    }

    private static Optional<Comment> findById(Long id){
        Comment result = null;
        if(result != null)
            return Optional.of(result);
        else
            return Optional.empty();
    }
}
