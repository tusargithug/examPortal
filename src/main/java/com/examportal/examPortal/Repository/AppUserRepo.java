package com.examportal.examPortal.Repository;

import com.examportal.examPortal.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, String> {

    Long countByUserName(String userName);

    Optional<AppUser> findByUserNameOrEmail(String userName,String email);

    Optional<AppUser> findByUserName(String username);

    Optional<AppUser> findByEmail( String email);
}
