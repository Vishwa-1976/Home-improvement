package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.example.demo.Entity.BudgetManagement;

public interface BudgetManagementRepository extends JpaRepository<BudgetManagement, Long> {

    // ✅ Find Budgets by Category (Custom JPA Method)
    List<BudgetManagement> findByCategory(String category);

    // ✅ JPQL: Find Budgets Where Allocated Amount is Greater Than a Given Value
    @Query("SELECT b FROM BudgetManagement b WHERE b.allocatedAmount > :amount")
    List<BudgetManagement> findBudgetsWithAllocatedAmountGreaterThan(@Param("amount") Double amount);

    // ✅ JPQL: Find Budgets Where Spent Amount is Less Than a Given Value
    @Query("SELECT b FROM BudgetManagement b WHERE b.spentAmount < :amount")
    List<BudgetManagement> findBudgetsWithSpentAmountLessThan(@Param("amount") Double amount);
}
