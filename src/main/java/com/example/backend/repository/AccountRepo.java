package com.example.backend.repository;


import com.example.backend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String name);
    Optional<Account> findByUsernameIgnoreCase(String username);
}
