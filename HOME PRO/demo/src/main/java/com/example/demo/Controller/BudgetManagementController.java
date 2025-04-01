package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.BudgetManagementService;
import com.example.demo.Entity.BudgetManagement;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/budget")
public class BudgetManagementController {

    @Autowired
    private BudgetManagementService service;

    // ✅ Get All Budgets
    @GetMapping
    public List<BudgetManagement> getAllBudgets() {
        return service.getAllBudgets();
    }

    // ✅ Get Budget by ID
    @GetMapping("/{id}")
    public Optional<BudgetManagement> getBudgetById(@PathVariable Long id) {
        return service.getBudgetById(id);
    }

    // ✅ Add a New Budget (Supports List)
    @PostMapping
    public List<BudgetManagement> addBudget(@RequestBody List<BudgetManagement> budgets) {
        return service.addBudget(budgets);
    }

    // ✅ Update Budget
    @PutMapping("/{id}")
    public BudgetManagement updateBudget(@PathVariable Long id, @RequestBody BudgetManagement updatedBudget) {
        return service.updateBudget(id, updatedBudget);
    }

    // ✅ Delete Budget
    @DeleteMapping("/{id}")
    public String deleteBudget(@PathVariable Long id) {
        return service.deleteBudget(id);
    }

    // ✅ Find by Category (Custom JPA Query)
    @GetMapping("/category/{category}")
    public List<BudgetManagement> getBudgetsByCategory(@PathVariable String category) {
        return service.getBudgetsByCategory(category);
    }

    // ✅ JPQL Query: Get Budgets Where Allocated Amount > X
    @GetMapping("/allocated/greater/{amount}")
    public List<BudgetManagement> getBudgetsWithAllocatedAmountGreaterThan(@PathVariable Double amount) {
        return service.getBudgetsWithAllocatedAmountGreaterThan(amount);
    }

    // ✅ JPQL Query: Get Budgets Where Spent Amount < X
    @GetMapping("/spent/less/{amount}")
    public List<BudgetManagement> getBudgetsWithSpentAmountLessThan(@PathVariable Double amount) {
        return service.getBudgetsWithSpentAmountLessThan(amount);
    }
}
