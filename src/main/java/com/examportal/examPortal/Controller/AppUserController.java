package com.examportal.examPortal.Controller;

import com.examportal.examPortal.Constant.AppConstant;
import com.examportal.examPortal.Dto.ChangePasswordDto;
import com.examportal.examPortal.Dto.LogInDto;
import com.examportal.examPortal.Dto.RegisterDto;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstant.Endpoints.APP_USER_END_POINT)
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/registration")
    public GenericResponse registration(@RequestBody RegisterDto registerDto) {
        return appUserService.registration(registerDto);
    }

    @PostMapping("/login")
    public GenericResponse logIn(@RequestBody LogInDto logInDto) {
        return appUserService.logIn(logInDto);
    }

    @PostMapping("/update")
    public GenericResponse updateUser(@RequestBody RegisterDto registerDto) {
        return appUserService.updateUser(registerDto);
    }

    @PostMapping("/change-password")
    public GenericResponse changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return appUserService.changePassword(changePasswordDto);
    }
}
