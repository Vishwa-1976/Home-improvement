package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Money;
import com.example.demo.Repositories.MoneyRepository;

@Service
public class MoneyService {
    @Autowired
    private MoneyRepository repository;

    // ✅ Add a new Money record
    public List<Money> addMoney(List<Money> money) {
        return repository.saveAll(money);
    }

    // ✅ Get All Money Records
    public List<Money> getAllMoneyRecords() {
        return repository.findAll();
    }

    // ✅ Get Money by ID
    public Optional<Money> getMoneyById(Long id) {
        return repository.findById(id);
    }

    // ✅ Update Money Record
    public Money updateMoney(Long id, Money updatedMoney) {
        return repository.findById(id)
            .map(existingMoney -> {
                existingMoney.setAmount(updatedMoney.getAmount());
                existingMoney.setCurrency(updatedMoney.getCurrency());
                return repository.save(existingMoney);
            }).orElseThrow(() -> new RuntimeException("Money record not found!"));
    }

    // ✅ Delete Money Record
    public String deleteMoney(Long id) {
        repository.deleteById(id);
        return "Money record ID " + id + " deleted successfully!";
    }

    // ✅ Find by Currency (Custom Query)
    public List<Money> getMoneyByCurrency(String currency) {
        return repository.findByCurrency(currency);
    }
    public List<Money> getMoneyGreaterThan(double amount) {
        return repository.findByAmountGreaterThan(amount);
    }
}
