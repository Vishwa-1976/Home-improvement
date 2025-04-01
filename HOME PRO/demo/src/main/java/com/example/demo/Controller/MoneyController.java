package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Money;
import com.example.demo.Service.MoneyService;

@RestController
@RequestMapping("/money")
public class MoneyController {

    @Autowired
    private MoneyService service;

    // ✅ Add a new Money record
    @PostMapping
    public List<Money> createMoney(@RequestBody List<Money> money) {
        return service.addMoney(money);
    }

    // ✅ Get all Money records
    @GetMapping
    public List<Money> getAllMoneyRecords() {
        return service.getAllMoneyRecords();
    }

    // ✅ Get Money record by ID
    @GetMapping("/{id}")
    public Optional<Money> getMoneyById(@PathVariable Long id) {
        return service.getMoneyById(id);
    }

    // ✅ Update Money record
    @PutMapping("/{id}")
    public Money updateMoney(@PathVariable Long id, @RequestBody Money updatedMoney) {
        return service.updateMoney(id, updatedMoney);
    }

    // ✅ Delete Money record
    @DeleteMapping("/{id}")
    public String deleteMoney(@PathVariable Long id) {
        return service.deleteMoney(id);
    }

    // ✅ Find by Currency (Custom Query)
    @GetMapping("/currency/{currency}")
    public List<Money> getMoneyByCurrency(@PathVariable String currency) {
        return service.getMoneyByCurrency(currency);
    }

    // ✅ Find Money records with amount greater than a given value
    @GetMapping("/amount/{amount}")
    public List<Money> getMoneyGreaterThan(@PathVariable double amount) {
        return service.getMoneyGreaterThan(amount);
    }
}
