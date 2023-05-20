package com.examportal.examPortal.Repository;

import com.examportal.examPortal.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,String> {

    Optional<AppUser> findByUserName(String userName);
}
