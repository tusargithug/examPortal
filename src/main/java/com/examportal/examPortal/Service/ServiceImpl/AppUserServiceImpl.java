package com.examportal.examPortal.Service.ServiceImpl;

import com.examportal.examPortal.Dto.*;
import com.examportal.examPortal.Enum.DeleteType;
import com.examportal.examPortal.Enum.Role;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.AppUser;
import com.examportal.examPortal.Repository.AppUserRepo;
import com.examportal.examPortal.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepo userRepo;

//    @Autowired
//    JavaMailSender javaMailSender;
//    @Value("${spring.mail.username}")
//    String from;

    private final JavaMailSender javaMailSender;

    public AppUserServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public GenericResponse registration(RegisterDto registerDto) {
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
        // user.setPassword(registerDto.getPassword());
        // user.setConfirmPassword(registerDto.getConfirmPassword());
        if (registerDto.getPassword().matches(registerDto.getConfirmPassword())) {
            user.setPassword(registerDto.getPassword());
        } else {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Password mismatch");
        }
        user.setUserName(registerDto.getUserName());
        user.setRoleType(Role.valueOf(registerDto.getRoleType()));
        userRepo.save(user);
        return new GenericResponse(HttpStatus.OK, "Registration done");
    }

    @Override
    public GenericResponse logIn(LogInDto logInDto) {
        Optional<AppUser> appUserOptional = userRepo.findByUserNameOrEmail(logInDto.getUserName(), logInDto.getEmail());

        if (appUserOptional.isEmpty()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid username or password ");
        }
        AppUser user = appUserOptional.get();
        if (user.getPassword().equals(logInDto.getPassword())) {
            return new GenericResponse(HttpStatus.OK, "Log in successfully");
        }
        return null;
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
        } else {
            userRepo.deleteById(deleteDto.getId());
        }
        return new GenericResponse(HttpStatus.OK, " User delete ");
    }

//    @Override
//    public void sendMailMesssage(MailDto mailDto) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(mailDto.getTo());
//        message.setSubject(mailDto.getSubject());
//        message.setText(mailDto.getText());
//        javaMailSender.send(message);
//       // return new GenericResponse(HttpStatus.OK , "Email Sent ");
//    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
