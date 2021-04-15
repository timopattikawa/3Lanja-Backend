package com.Lanja.finnancial.repositories;

import com.Lanja.finnancial.entity.ApplicationUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUserDetails, Integer> {
    Optional<ApplicationUserDetails> findUserByUserName(String userName);
}
