package com.examportal.examPortal.Model;


import com.examportal.examPortal.Enum.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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



}
