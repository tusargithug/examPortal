package com.examportal.examPortal.Service.ServiceImpl;

import com.examportal.examPortal.Dto.*;
import com.examportal.examPortal.Enum.DeleteType;
import com.examportal.examPortal.Enum.OTPType;
import com.examportal.examPortal.Enum.OtpStatus;
import com.examportal.examPortal.Enum.Role;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.AppUser;
import com.examportal.examPortal.Model.Otp;
import com.examportal.examPortal.Repository.AppUserRepo;
import com.examportal.examPortal.Repository.OtpRepo;
import com.examportal.examPortal.Service.AppUserService;
import com.examportal.examPortal.Util.ServiceUtility;
import com.examportal.examPortal.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepo userRepo;

    @Autowired
    private OtpRepo otpRepo;
    //Below are for security
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private final MailService mailService;

    private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

    public AppUserServiceImpl(MailService mailService) {
        this.mailService = mailService;
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
        Optional<AppUser> appUserOptional = userRepo.findByEmail(registerDto.getEmail());
        if(appUserOptional.isPresent()){
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Email is already register");
        }
        String email = registerDto.getEmail();
        AppUser user = new AppUser();

        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setRollNo(registerDto.getRollNo());
        user.setMobileNo(registerDto.getMobileNo());
        user.setEmail(email);
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
        mailService.sendMail(email,"Exam Portal registration","Welcome to Our Exam portal");
        return new GenericResponse(HttpStatus.OK, "Registration done");
    }

    //TODO when otp type is forgot password
    @Override
    public GenericResponse generateOtp(GenerateOtpDto generateOtpDto) {
        if (generateOtpDto.getEmail().isEmpty()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Email id is missing");
        }
        if (generateOtpDto.getOtpType().toString().isEmpty()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Otp type is missing");
        }
        if (generateOtpDto.getOtpType().equals(OTPType.FORGOT_PASSWORD)) {
            Optional<AppUser> appUser = userRepo.findByEmail(generateOtpDto.getEmail());
            if (appUser.isPresent()) {
                String logInOtp = ServiceUtility.yourOtp();
                String otpChar = ServiceUtility.generateAlphaCharacter(4);
                Otp otp = new Otp();
                otp.setOTP(logInOtp);
                otp.setOTPChar(otpChar);
                otp.setOTPType(generateOtpDto.getOtpType());
                otp.setOtpStatus(OtpStatus.PENDING);
                otp.setExpiryDateTime(LocalDateTime.now().plusMinutes(5));
                otp.setUser(appUser.get());
                otpRepo.save(otp);
                return new GenericResponse(HttpStatus.OK, logInOtp);
            }
        }
        return null;
    }

    @Override
    public GenericResponse otpVerification(OtpVerificationDto otpVerificationDto) {
        Optional<Otp> otpOptional = otpRepo.findByOTPAndOTPType(otpVerificationDto.getOtp(), OTPType.SIGN_IN);
        if (otpOptional.isEmpty()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid otp to login");
        }
        if (otpOptional.isPresent()) {
            Otp otp = otpOptional.get();
            if (otp.getExpiryDateTime().equals(LocalDateTime.now()) || otp.getExpiryDateTime().isBefore(LocalDateTime.now())) {
                return new GenericResponse(HttpStatus.BAD_REQUEST, "Otp expired");
            } else {
                otp.setOtpStatus(OtpStatus.SUCCESS);
                otpRepo.save(otp);
                //TODO if requirement is  login with any otp verification
                String token = jwtService.generateJwtTokenForEmail(otpVerificationDto.getEmail());
                return new GenericResponse(HttpStatus.OK, "Otp verified ", token);
            }

        }
        return null;
    }

    @Override
    public GenericResponse logIn(LogInDto logInDto) {
        Optional<AppUser> appUserOptional = userRepo.findByEmail(logInDto.getEmail());

        if (appUserOptional.isEmpty()) {
            return new GenericResponse(HttpStatus.UNAUTHORIZED, "Invalid username ");
        }
        AppUser user = appUserOptional.get();
        BCryptPasswordEncoder encodePassword = new BCryptPasswordEncoder();
        if (encodePassword.matches(logInDto.getPassword(), user.getPassword())) {
            String logInOtp = ServiceUtility.yourOtp();
            String otpChar = ServiceUtility.generateAlphaCharacter(4);
            Otp otp = new Otp();
            otp.setOTP(logInOtp);
            otp.setOTPChar(otpChar);
            otp.setOTPType(OTPType.SIGN_IN);
            otp.setOtpStatus(OtpStatus.PENDING);
            otp.setExpiryDateTime(LocalDateTime.now().plusMinutes(5));
            otp.setUser(user);
            otpRepo.save(otp);
            return new GenericResponse(HttpStatus.OK, "Otp generated", otpChar);
        } else {
            return new GenericResponse(HttpStatus.OK, "Invalid password");
        }


        //TODO if requirement is directly to login with out any otp verification
        //   Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(otpVerificationDto.getEmail(), otpVerificationDto.getPassword()));
        //  if (authentication.isAuthenticated()) {
        //      String token = jwtService.generateJwtToken(authentication);
        //      return new GenericResponse(HttpStatus.OK, "Otp verified ", token);
        //      } else {
        //       return new GenericResponse(HttpStatus.BAD_REQUEST, "Invalid user name or password");
        //     }
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
            return new GenericResponse(HttpStatus.OK, "User status change");
        } else {
            userRepo.deleteById(deleteDto.getId());
        }
        return new GenericResponse(HttpStatus.OK, " User delete ");
    }

    @Override
    public GenericResponse getAll() {
        List<AppUser> appUsers = userRepo.findAll();
        return new GenericResponse(HttpStatus.OK, appUsers);
    }

    @Override
    public GenericResponse getById(String id) {
        Optional<AppUser> appUserOptional = userRepo.findById(id);
        if (appUserOptional.isEmpty()) {
            return new GenericResponse(HttpStatus.BAD_REQUEST, "User not found ");
        }
        AppUser user = appUserOptional.get();
        RegisterDto registerDto = new RegisterDto();
        registerDto.setId(user.getId());
        registerDto.setEmail(user.getEmail());

        return new GenericResponse(HttpStatus.OK, registerDto);
    }


}
