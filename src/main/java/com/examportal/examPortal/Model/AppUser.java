package com.examportal.examPortal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUser extends BaseEntity{
private String firstName;
private String lastName;
private String rollNo;
private String mobileNo;
private String email;
private String password;
private String confirmPassword;

}
