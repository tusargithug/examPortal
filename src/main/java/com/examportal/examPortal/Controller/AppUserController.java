package com.examportal.examPortal.Controller;

        import com.examportal.examPortal.Constant.AppConstant;
        import com.examportal.examPortal.Dto.*;
        import com.examportal.examPortal.Generic.GenericResponse;
        import com.examportal.examPortal.Model.AuthenticationResponse;
        import com.examportal.examPortal.Service.AppUserService;
        import com.examportal.examPortal.Service.ServiceImpl.AppUserServiceImpl;
        import org.springframework.beans.factory.annotation.Autowired;
  //      import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.security.Principal;

@RestController
@RequestMapping(AppConstant.Endpoints.APP_USER_END_POINT)
public class AppUserController {

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

//    @PostMapping("/send-email")
//    public void emailSent(@RequestBody MailDto mailDto) {
//         appUserService.sendMailMesssage(mailDto);
//    }


//    @PostMapping("/send-email")
//    public void sendEmail(@RequestBody MailDto emailDto) {
//        String to = emailDto.getTo();
//        String subject = emailDto.getSubject();
//        String text = emailDto.getText();
//
//        appUserServiceImpl.sendEmail(to, subject, text);
//    }
}
