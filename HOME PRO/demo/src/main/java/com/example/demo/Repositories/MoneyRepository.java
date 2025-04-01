package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Money;
import java.util.List;

public interface MoneyRepository extends JpaRepository<Money, Long> {
    // ✅ Custom Query: Find by currency
    List<Money> findByCurrency(String currency);

    @Query("SELECT m FROM Money m WHERE m.amount > :amount")
    List<Money> findByAmountGreaterThan(@Param("amount") double amount);
}
