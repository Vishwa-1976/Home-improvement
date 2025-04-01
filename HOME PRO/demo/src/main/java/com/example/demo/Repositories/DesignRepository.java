package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.example.demo.Entity.Design;

public interface DesignRepository extends JpaRepository<Design, Long> {
    // ✅ Custom Query: Find by title
    @Query("SELECT d FROM Design d WHERE " +
       "LOWER(d.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
       "LOWER(d.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Design> findByTitle(@Param("keyword") String keyword);

    List<Design> save(List<Design> design);
}
