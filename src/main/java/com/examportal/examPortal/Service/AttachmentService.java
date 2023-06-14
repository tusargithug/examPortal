package com.examportal.examPortal.Service;

import com.examportal.examPortal.Model.Attachment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AttachmentService {
    Attachment uploadFile(MultipartFile file);
}
