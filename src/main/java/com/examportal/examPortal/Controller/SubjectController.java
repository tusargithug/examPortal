package com.examportal.examPortal.Controller;

import com.examportal.examPortal.Constant.AppConstant;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstant.Endpoints.SUBJECT_END_POINT)
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("add")
    public GenericResponse addSubject(@RequestParam("name") String name) {
        return subjectService.addSubject(name);
    }
}
