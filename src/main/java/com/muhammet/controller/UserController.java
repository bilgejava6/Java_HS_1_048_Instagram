package com.muhammet.controller;

import com.muhammet.entity.User;
import com.muhammet.service.UserService;
import com.muhammet.utility.Response;

import java.util.Scanner;

public class UserController {
    private final UserService userService;
    public UserController(){
        this.userService = new UserService();
    }
    public void register(){
        boolean isRegister= false;
        do{
            System.out.println("""
                ************************************
                ******      ÜYE OL   ***************
                ************************************
                """);
            System.out.print("Kullanıcı adı.........: ");
            String userName = new Scanner(System.in).nextLine();
            System.out.print("Kullanıcı email.......: ");
            String email = new Scanner(System.in).nextLine();
            System.out.print("Şifre.................: ");
            String password = new Scanner(System.in).nextLine();
            System.out.print("Şifre Doğrulama.......: ");
            String rePassword = new Scanner(System.in).nextLine();
            /**
             * 1- Eğer şifreler uyuşmuyor ise, tekrar giriş yapması sağlanır. (CONTROLLER)
             * 2- Bilgiler zorunlydur boş bırakılamaz ve eksik girilemez. (CONTROLLER)
             * ***** ATAHAN BİZİMLE MİSİN?  *****
             *
             */
            if(!password.equals(rePassword)){
                System.out.println("Şifreler uyuşmuyor, lütfen düzeltin.");
            }else if(userName.length()<3 || userName.length()>64){
                System.out.println("Kullanıcı adı 3-64 karakter arasında olmalıdır.");
            } else if (!email.contains("@")) {
                System.out.println("Lütfen e-mail formatında giriş yapınız.");
            }else {
              Response<Boolean> response =  userService.register(userName,email,password);
              isRegister = response.getData();
              System.out.println(response.getMessage());
            }
        }while (!isRegister);


    }

    public void login(){
        System.out.println("""
                ***********************************
                ******** Giriş Yap ****************
                ***********************************                
                """);
        System.out.print("Kullanıcı adı......: ");
        String userName = new Scanner(System.in).nextLine();
        System.out.print("Şifre..............: ");
        String password = new Scanner(System.in).nextLine();
        Response<User> response = userService.login(userName,password);
        if (response.getStatusCode()==400 || response.getStatusCode()==500){
            System.out.println(response.getMessage());
        }else {
            /**
             * giriş başarılı oldu işlemleri yapılacak. geri dönen kullanıcı bilgisi
             * uygulama içinde static olarak saklanacak(Mobil uygulamalarda lokal de saklanır.)
             */

        }
    }
}
