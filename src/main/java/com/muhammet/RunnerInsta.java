package com.muhammet;

import com.muhammet.controller.UserController;

import java.util.Scanner;

public class RunnerInsta {
    public static void main(String[] args) {
        UserController userController = new UserController();
        int secim;
        do{
            System.out.println("""
                ***************************************
                **********  JAVA HS 1 INSTA  **********
                ***************************************
                1- Login
                2- Register
                0- Exit
                """);
            System.out.print("seçin........: ");
            secim = new Scanner(System.in).nextInt();
            switch (secim){
                case 1:  break;
                case 2: userController.register(); break;
                case 0:
                    System.out.println("ÇIKIŞ YAPILDI");break;
                default:
                    System.out.println("Hatalı seçim lütfen tekrar deneyiniz.");
            }
        }while (secim!=0);


    }
}
