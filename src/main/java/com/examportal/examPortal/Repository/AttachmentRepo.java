package com.examportal.examPortal.Repository;

import com.examportal.examPortal.Model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepo extends JpaRepository<Attachment,String> {
}
