package com.examportal.examPortal.Controller;

import com.examportal.examPortal.Constant.AppConstant;
import com.examportal.examPortal.Dto.*;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.MailStructure;
import com.examportal.examPortal.Service.AppUserService;
import com.examportal.examPortal.Service.ServiceImpl.AppUserServiceImpl;
import com.examportal.examPortal.Service.ServiceImpl.MailService;
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

    @Autowired
    private MailService mailService;

    public AppUserController(AppUserServiceImpl emailService) {
        this.appUserServiceImpl = emailService;
    }

    @PostMapping("/registration")
    public GenericResponse registration(@RequestBody RegisterDto registerDto) {
        return appUserService.registration(registerDto);
    }

    @PostMapping("/generate-otp")
    public GenericResponse generateOtp(@RequestBody GenerateOtpDto generateOtpDto) {
        return appUserService.generateOtp(generateOtpDto);
    }

    @PostMapping("/otp-verification")
    public GenericResponse otpVerification(@RequestBody OtpVerificationDto otpVerificationDto) {
        return appUserService.otpVerification(otpVerificationDto);
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
//    @PreAuthorize("hasAnyAuthority('STUDENT')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("get-all")
    public GenericResponse getAll() {
        return appUserService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("get-by-id")
    public GenericResponse getById(String id) {
        return appUserService.getById(id);
    }

    @PostMapping("/send")
    public String mailSent(@RequestParam("mail") String mail, @RequestBody MailStructure mailStructure){
        mailService.sendMail(mail,mailStructure);
        return "Successfully sent the mail";
    }
}
