package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Contractor;
import com.example.demo.Repositories.ContractorRepository;

@Service
public class ContractorService {
    @Autowired
    private ContractorRepository repository;

    // ✅ Add Contractor
    public List<Contractor> addContractor(List<Contractor> contractors) { 
        return repository.saveAll(contractors);
    }
    

    // ✅ Get Contractor by ID
    public Optional<Contractor> getContractorById(Long id) {
        return repository.findById(id);
    }

    // ✅ Get All Contractors
    public List<Contractor> getAllContractors() {
        return repository.findAll();
    }

    // ✅ Get Contractors by Name
    public List<Contractor> getContractorsByName(String name) {
        return repository.findByName(name);
    }

    // ✅ Update Contractor
    public Contractor updateContractor(Long id, Contractor updatedContractor) {
        return repository.findById(id)
            .map(existingContractor -> {
                existingContractor.setName(updatedContractor.getName());
                existingContractor.setSpecialization(updatedContractor.getSpecialization());
                existingContractor.setRating(updatedContractor.getRating());
                return repository.save(existingContractor);
            })
            .orElseThrow(() -> new RuntimeException("Contractor not found!"));
    }

    // ✅ Delete Contractor
    public String deleteContractor(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Contractor with ID " + id + " successfully deleted!";
        } else {
            throw new RuntimeException("Contractor not found!");
        }
    }
}
