package com.examportal.examPortal.Model;


import com.examportal.examPortal.Enum.Role;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser extends BaseEntity  {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "roll_no")
    private String rollNo;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Enumerated
    @Column(name = "role", nullable = false)
    private Role roleType;

    @Column(name = "is_active")
    private Boolean isActive=Boolean.TRUE;

    public AppUser(AppUser appUser) {
    }



}
