package com.examportal.examPortal.Service;

import com.examportal.examPortal.Dto.RegisterDto;
import com.examportal.examPortal.Generic.GenericResponse;

public interface AppUserService {
    public GenericResponse registration(RegisterDto registerDto);
}
