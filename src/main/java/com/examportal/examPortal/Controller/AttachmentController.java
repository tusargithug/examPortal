package com.examportal.examPortal.Controller;

import com.examportal.examPortal.Constant.AppConstant;
import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.Attachment;
import com.examportal.examPortal.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(AppConstant.Endpoints.APP_USER_END_POINT)
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/upload")
    public GenericResponse uploadFile(@RequestParam("file") MultipartFile file) {
        Attachment attachment = null;
        attachment = attachmentService.uploadFile(file);
        return null;
    }
}
