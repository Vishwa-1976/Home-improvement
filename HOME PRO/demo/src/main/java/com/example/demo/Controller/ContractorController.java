package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Contractor;
import com.example.demo.Service.ContractorService;

@RestController
@RequestMapping("/contractors")
public class ContractorController {

    @Autowired
    private ContractorService service;

    // ✅ Create Contractor
    @PostMapping
    public List<Contractor> createContractor(@RequestBody List<Contractor> contractor) {
        return service.addContractor(contractor);
    }

    // ✅ Get Contractor by ID
    @GetMapping("/{id}")
    public Optional<Contractor> getContractorById(@PathVariable Long id) {
        return service.getContractorById(id);
    }

    // ✅ Get All Contractors
    @GetMapping
    public List<Contractor> getAllContractors() {
        return service.getAllContractors();
    }

    // ✅ Get Contractors by Name
    @GetMapping("/name/{name}")
    public List<Contractor> getContractorsByName(@PathVariable String name) {
        return service.getContractorsByName(name);
    }

    // ✅ Update Contractor
    @PutMapping("/{id}")
    public Contractor updateContractor(@PathVariable Long id, @RequestBody Contractor updatedContractor) {
        return service.updateContractor(id, updatedContractor);
    }

    // ✅ Delete Contractor
    @DeleteMapping("/{id}")
    public String deleteContractor(@PathVariable Long id) {
        return service.deleteContractor(id);
    }
}
