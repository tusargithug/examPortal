package com.examportal.examPortal.Service;

import com.examportal.examPortal.Generic.GenericResponse;
import org.springframework.stereotype.Service;


public interface SubjectService {
    GenericResponse addSubject(String name);
}
