package com.examportal.examPortal.Service.ServiceImpl;

import com.examportal.examPortal.Dto.RegisterDto;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.AppUser;

import com.examportal.examPortal.Repository.AppUserRepo;
import com.examportal.examPortal.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepo UserRepo;

    @Override
    public GenericResponse registration(RegisterDto registerDto) {
        Optional<AppUser> appUserOptional = UserRepo.findByUserName(registerDto.getUserName());
        if (appUserOptional.isPresent()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid User Name");
        }

        List<AppUser> appUserList=new ArrayList<>();
        AppUser user = new AppUser();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setRollNo(registerDto.getRollNo());
        user.setMobileNo(registerDto.getMobileNo());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setConfirmPassword(registerDto.getConfirmPassword());
        user.setUserName(registerDto.getUserName());
       // user.setRoleType(Role.valueOf(registerDto.getRoleType()));
        appUserList.add(user);


        return new GenericResponse(HttpStatus.OK, "Registration done");
    }
}
