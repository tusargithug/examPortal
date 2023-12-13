package com.examportal.examPortal;


import com.examportal.examPortal.Enum.Role;
import com.examportal.examPortal.Model.AppUser;
import com.examportal.examPortal.Repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ExamPortalApplication {
        //implements CommandLineRunner
@Autowired
private AppUserRepo userRepo;
    public static void main(String[] args) {
        SpringApplication.run(ExamPortalApplication.class, args);
        System.out.println("===========================================");
        System.out.println("===========================================");
        System.out.println("======Exam Portal Start");
        System.out.println("===========================================");
        System.out.println("===========================================");


    }
//    @Override
//    public void run(String... args) {
//        appUser();
//    }
//
//    public void appUser(){
//        AppUser user =  new AppUser();
//        user.setUserName("Abhisek");
//        user.setRoleType(Role.STUDENT);
//        userRepo.save(user);
//    }
}
