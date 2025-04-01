package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ Properly override findAll(Pageable pageable)
    @Override
    Page<User> findAll(Pageable pageable);

    // ✅ Find users by name
    List<User> findByName(String name);

    // ✅ Find user by email
    List<User> findByEmail(String email);
}

