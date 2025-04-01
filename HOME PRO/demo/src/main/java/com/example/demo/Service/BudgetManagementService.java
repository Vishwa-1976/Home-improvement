package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repositories.BudgetManagementRepository;
import com.example.demo.Entity.BudgetManagement;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetManagementService {
    @Autowired
    private BudgetManagementRepository repository;

    // Get All Budgets
    public List<BudgetManagement> getAllBudgets() {
        return repository.findAll();
    }

    // Get Budget by ID
    public Optional<BudgetManagement> getBudgetById(Long id) {
        return repository.findById(id);
    }

    // Add a New Budget (Supports Multiple Entries)
    public List<BudgetManagement> addBudget(List<BudgetManagement> budgets) {
        return repository.saveAll(budgets);
    }

    // Update Budget
    public BudgetManagement updateBudget(Long id, BudgetManagement updatedBudget) {
        return repository.findById(id)
            .map(existingBudget -> {
                existingBudget.setCategory(updatedBudget.getCategory());
                existingBudget.setAllocatedAmount(updatedBudget.getAllocatedAmount());
                existingBudget.setSpentAmount(updatedBudget.getSpentAmount());
                return repository.save(existingBudget);
            })
            .orElseThrow(() -> new RuntimeException("Budget not found!"));
    }

    // Delete Budget
    public String deleteBudget(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Budget with ID " + id + " successfully deleted!";
        } else {
            throw new RuntimeException("Budget not found!");
        }
    }

    // Custom JPA Query - Find by Category
    public List<BudgetManagement> getBudgetsByCategory(String category) {
        return repository.findByCategory(category);
    }
    
    // ✅ Find Budgets with Allocated Amount Greater Than a Given Value (JPQL)
    public List<BudgetManagement> getBudgetsWithAllocatedAmountGreaterThan(Double amount) {
        return repository.findBudgetsWithAllocatedAmountGreaterThan(amount);
    }

    // ✅ Find Budgets with Spent Amount Less Than a Given Value (JPQL)
    public List<BudgetManagement> getBudgetsWithSpentAmountLessThan(Double amount) {
        return repository.findBudgetsWithSpentAmountLessThan(amount);
    }
}
