package com.examportal.examPortal.Repository;

import com.examportal.examPortal.Model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, String> {
    Optional<Subject> findByName(String name);
}
