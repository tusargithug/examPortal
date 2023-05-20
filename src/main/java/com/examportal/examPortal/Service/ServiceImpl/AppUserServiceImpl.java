package com.examportal.examPortal.Service.ServiceImpl;

import com.examportal.examPortal.Dto.RegisterDto;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.AppUser;
import com.examportal.examPortal.Enum.Role;
import com.examportal.examPortal.Repository.AppUserRepo;
import com.examportal.examPortal.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public GenericResponse registration(RegisterDto registerDto) {
        Optional<AppUser> appUserOptional = appUserRepo.findByUserName(registerDto.getUserName());
        if (appUserOptional.isPresent()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid User Name");
        }
        String patternForFirstName = "^[A-Z][a-zA-Z]*$";

        AppUser user = new AppUser();
      //  if(registerDto.getFirstName().matches(patternForFirstName)) {
            user.setFirstName(registerDto.getFirstName());
      //  }
       // else{
        //    return new GenericResponse(HttpStatus.BAD_REQUEST,"Invalid pattern for name");
        //}
        user.setLastName(registerDto.getLastName());
        user.setRollNo(registerDto.getRollNo());
        user.setMobileNo(registerDto.getMobileNo());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setConfirmPassword(registerDto.getConfirmPassword());
        user.setUserName(registerDto.getUserName());
        user.setRoleType(Role.valueOf(registerDto.getRoleType()));
        appUserRepo.save(user);

        return new GenericResponse(HttpStatus.OK, "Registration done");
    }
}
