package com.muhammet.controller;

import com.muhammet.service.PostService;

import java.util.Scanner;

public class PostController {
    private final PostService postService;
    private PostController(){
        this.postService = new PostService();
    }

    /**
     * Instagram' ın Gerçek çalışma Süreci
     * -> Login (username,password)
     * -> Login işlemi yapıldıktan sonra giriş yapan kullanıcıya benzersiz bir TOKEN üretilir ve iletilir.
     * bu token henellikle (JWT) şeklinde kullanılır.
     * -> JWT ilgili cihazda saklanır ve işlem yapılırken bu kullanılır.
     * -> POST Atarken, yorum yazarken, like atarken kimlik doğrulama işlemi için JWT kullanılır.
     */
    public void newPost(){
        System.out.println("""
                **************************************
                *****       POST OLUŞTUR      ********
                **************************************
                """);
        System.out.print("Kullanıcı adı......: ");
        String userName = new Scanner(System.in).nextLine();
        System.out.print("Açıklama...........: ");
        String comment = new Scanner(System.in).nextLine();
        System.out.print("Resim..............: ");
        String imageUrl = new Scanner(System.in).nextLine();
        System.out.print("Adres..............: ");
        String location = new Scanner(System.in).nextLine();

    }
}
