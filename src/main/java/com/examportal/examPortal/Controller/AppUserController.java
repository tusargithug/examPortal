package com.examportal.examPortal.Controller;

import com.examportal.examPortal.Constant.AppConstant;
import com.examportal.examPortal.Dto.RegisterDto;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstant.Endpoints.APP_USER_END_POINT)
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/registration")
    public GenericResponse registration(@RequestBody RegisterDto registerDto) {
        return appUserService.registration(registerDto);
    }
}
