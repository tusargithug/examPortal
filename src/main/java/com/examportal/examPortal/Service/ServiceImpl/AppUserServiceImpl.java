package com.examportal.examPortal.Service.ServiceImpl;

import com.examportal.examPortal.Dto.*;
import com.examportal.examPortal.Enum.DeleteType;
import com.examportal.examPortal.Enum.Role;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.AppUser;
import com.examportal.examPortal.Repository.AppUserRepo;
import com.examportal.examPortal.Service.AppUserService;
import com.examportal.examPortal.emailService.MailService;

import com.examportal.examPortal.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepo userRepo;

    @Autowired
    private MailService mailService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Override
    public GenericResponse  registration(RegisterDto registerDto) {
        Long countByUserName = userRepo.countByUserName(registerDto.getUserName());
        if (countByUserName >= 1) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid User Name");
        }
        String formatForFirstName = "^[A-Z][a-z]*$";
        String formatForLastName = "^[A-Z][a-z]*$";
        if (!registerDto.getFirstName().matches(formatForFirstName)) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid first name format");
        }
        if (!registerDto.getLastName().matches(formatForLastName)) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid last name format");
        }
        AppUser user = new AppUser();

        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setRollNo(registerDto.getRollNo());
        user.setMobileNo(registerDto.getMobileNo());
        user.setEmail(registerDto.getEmail());
         user.setPassword(registerDto.getPassword());
        if (registerDto.getPassword().matches(registerDto.getConfirmPassword())) {
            user.setPassword(registerDto.getPassword());
        } else {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Password mismatch");
        }
        BCryptPasswordEncoder encodePassword = new BCryptPasswordEncoder();
         user.setPassword(encodePassword.encode(registerDto.getPassword()));
        user.setUserName(registerDto.getUserName());
        user.setRoleType(Role.valueOf(registerDto.getRoleType()));
        userRepo.save(user);

        return new GenericResponse(HttpStatus.OK, "Registration done");
    }

    @Override
    public GenericResponse logIn(LogInDto logInDto) throws MailException {
//        Optional<AppUser> appUserOptional = userRepo.findByEmail(logInDto.getEmail());
//
//        if (appUserOptional.isEmpty()) {
//            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid username or password ");
//        }
//        AppUser user = appUserOptional.get();
//        BCryptPasswordEncoder encodePassword = new BCryptPasswordEncoder();
//        if(encodePassword.encode(logInDto.getPassword()).matches(user.getPassword())){
//            return new GenericResponse(HttpStatus.OK, "Log in successfully");
//        }
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(logInDto.getEmail(), logInDto.getPassword());
        authManager.authenticate(authInputToken);
        String token = jwtService.generateJwtToken(authInputToken);
        return new GenericResponse(HttpStatus.OK, "Log in successfully");

    }

    @Override
    public GenericResponse updateUser(RegisterDto registerDto) {
        Optional<AppUser> appUserOptional = userRepo.findById(registerDto.getId());
        if (appUserOptional.isEmpty()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, " Invalid user to update ");
        }
        AppUser user = appUserOptional.get();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setRollNo(registerDto.getRollNo());
        user.setMobileNo(registerDto.getMobileNo());
        user.setEmail(registerDto.getEmail());
        user.setUserName(registerDto.getUserName());
        user.setRoleType(Role.valueOf(registerDto.getRoleType()));
        userRepo.save(user);
        return new GenericResponse(HttpStatus.OK, "Profile update successfully");
    }

    @Override
    public GenericResponse changePassword(ChangePasswordDto changePasswordDto) {
        Optional<AppUser> appUserOptional = userRepo.findById(changePasswordDto.getId());
        if (appUserOptional.isEmpty()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid User to change password");
        }
        AppUser user = appUserOptional.get();
        if (user.getPassword().equals(changePasswordDto.getOldPassword())) {
            if (changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
                user.setPassword(changePasswordDto.getNewPassword());
                userRepo.save(user);
                return new GenericResponse(HttpStatus.OK, "Password change successfully done");
            } else {
                return new GenericResponse(HttpStatus.BAD_REQUEST, "Password Mismatch");
            }
        } else {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Wrong password");
        }

    }

    @Override
    public GenericResponse deleteById(DeleteDto deleteDto) {
        Optional<AppUser> appUserOptional = userRepo.findById(deleteDto.getId());
        if (appUserOptional.isEmpty()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid user");
        }
        AppUser user = appUserOptional.get();
        DeleteType deleteType = DeleteType.valueOf(deleteDto.getDeleteType());
        if (DeleteType.SOFT.equals(deleteType)) {
            user.setIsActive(Boolean.FALSE);
            userRepo.save(user);
            return new GenericResponse(HttpStatus.OK,"User status change");
        } else {
            userRepo.deleteById(deleteDto.getId());
        }
        return new GenericResponse(HttpStatus.OK, " User delete ");
    }

    @Override
    public GenericResponse getAll() {
        List<AppUser> appUsers = userRepo.findAll();
        return new GenericResponse(HttpStatus.OK,appUsers);
    }
}
