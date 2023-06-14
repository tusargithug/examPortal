package com.examportal.examPortal.Service.ServiceImpl;

import com.examportal.examPortal.Generic.GenericResponse;
import com.examportal.examPortal.Model.Attachment;
import com.examportal.examPortal.Repository.AttachmentRepo;
import com.examportal.examPortal.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Override
    public Attachment uploadFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("File name contains invalid path sequence");
            }
            Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
            return attachmentRepo.save(attachment);
        } catch (Exception e) {

        }
        return null;
    }
}
