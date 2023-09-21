package com.examportal.examPortal.Controller;

        import com.examportal.examPortal.Constant.AppConstant;
        import com.examportal.examPortal.Dto.*;
        import com.examportal.examPortal.Generic.GenericResponse;
        import com.examportal.examPortal.Service.AppUserService;
        import com.examportal.examPortal.Service.ServiceImpl.AppUserServiceImpl;
        import com.examportal.examPortal.security.JwtService;
        import org.springframework.beans.factory.annotation.Autowired;
  //      import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstant.Endpoints.APP_USER_END_POINT)
public class AppUserController {
    @Autowired
    JwtService jtwService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserServiceImpl appUserServiceImpl;
    public AppUserController(AppUserServiceImpl emailService) {
        this.appUserServiceImpl = emailService;
    }
    @PostMapping("/registration")
    public GenericResponse registration(@RequestBody RegisterDto registerDto) {
        return appUserService.registration(registerDto);
    }

    @PostMapping("/login")
    public GenericResponse logIn(@RequestBody LogInDto logInDto) {
        return appUserService.logIn(logInDto);
    }

//    @PostMapping("/token")
//    public String tokenGenerate(@RequestBody LogInDto logInDto) {
//        return jtwService.
//                generateToken(logInDto.getEmail());
//    }
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping("/update")
    public GenericResponse updateUser(@RequestBody RegisterDto registerDto) {
        return appUserService.updateUser(registerDto);
    }
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/change-password")
    public GenericResponse changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return appUserService.changePassword(changePasswordDto);
    }

    public GenericResponse deleteById(@RequestBody DeleteDto deleteDto) {

        return appUserService.deleteById(deleteDto);
    }
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("get-all")
    public GenericResponse getById() {
        return appUserService.getAll();
    }
}
