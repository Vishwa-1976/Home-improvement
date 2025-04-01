package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Contractor;

import java.util.List;


public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    List<Contractor> findByName(String name);  // ✅ Make sure it returns List<Contractor>
}
