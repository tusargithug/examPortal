package com.examportal.examPortal.Service.ServiceImpl;

import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.Subject;
import com.examportal.examPortal.Repository.SubjectRepo;
import com.examportal.examPortal.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepo subjectRepo;
    @Override
    public GenericResponse addSubject(String name) {
        Optional<Subject> subjectOptional =  subjectRepo.findByName(name);
        if(subjectOptional.isPresent()){
            return new GenericResponse(HttpStatus.OK, "Duplicate subject");
        }
        Subject subject = new Subject();
        subject.setName(name);
        subjectRepo.save(subject);
        return new GenericResponse(HttpStatus.OK, "Subject saved successfully");
    }
}
