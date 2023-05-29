package com.examportal.examPortal.Service.ServiceImpl;

import com.examportal.examPortal.Dto.LogInDto;
import com.examportal.examPortal.Dto.RegisterDto;
import com.examportal.examPortal.Enum.Role;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.AppUser;

import com.examportal.examPortal.Repository.AppUserRepo;
import com.examportal.examPortal.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepo userRepo;

    @Override
    public GenericResponse registration(RegisterDto registerDto) {
        Long countByUserName = userRepo.countByUserName(registerDto.getUserName());
        if (countByUserName>=1) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid User Name");
        }
        String formatForFirstName = "^[A-Z][a-z]*$";
        String formatForLastName = "^[A-Z][a-z]*$";
        if(!registerDto.getFirstName().matches(formatForFirstName)){
            return new GenericResponse(HttpStatus.BAD_REQUEST,"Invalid first name format");
        }
        if(!registerDto.getLastName().matches(formatForLastName)){
            return new GenericResponse(HttpStatus.BAD_REQUEST,"Invalid last name format");
        }
        AppUser user = new AppUser();

        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setRollNo(registerDto.getRollNo());
        user.setMobileNo(registerDto.getMobileNo());
        user.setEmail(registerDto.getEmail());
       // user.setPassword(registerDto.getPassword());
       // user.setConfirmPassword(registerDto.getConfirmPassword());
        if(registerDto.getPassword().matches(registerDto.getConfirmPassword())){
            user.setPassword(registerDto.getPassword());
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
            return new GenericResponse(HttpStatus.BAD_REQUEST,"Invalid user ");
        }
        AppUser user=appUserOptional.get();
        if(user.getPassword().matches(logInDto.getPassword())){
            return new GenericResponse(HttpStatus.OK,"Log in successfully");
        }
        return null;
    }
}
