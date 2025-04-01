package com.example.demo.Repositories;

// import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);  // Find by Username
    Optional<Admin> findByEmail(String email);  // Find by Email
    // List<Admin> saveAll(Admin admin);
}
