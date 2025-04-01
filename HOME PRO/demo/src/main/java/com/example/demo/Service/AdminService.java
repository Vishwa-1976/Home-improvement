package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.AdminRepository;
import com.example.demo.Entity.Admin;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    // ✅ Get Admin by ID
    public Optional<Admin> getAdminById(Long id) {
    return repository.findById(id);
}

    // ✅ Change method to accept a List<Admin>
    public List<Admin> addAdmin(List<Admin> admins) {
    return repository.saveAll(admins); // Bulk save all admins
    }

    
    public List<Admin> getAllAdmins() {
        return repository.findAll();
    }
    // Get Admin by Username
    public Optional<Admin> getAdminByUsername(String username) {
        return repository.findByUsername(username);
    }

    // Get Admin by Email
    public Optional<Admin> getAdminByEmail(String email) {
        return repository.findByEmail(email);
    }

    // Get All Admins with Pagination & Sorting
    public Page<Admin> getAllAdmins(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // Update Admin
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        return repository.findById(id)
            .map(existingAdmin -> {
                existingAdmin.setUsername(updatedAdmin.getUsername());
                existingAdmin.setEmail(updatedAdmin.getEmail());
                existingAdmin.setPassword(updatedAdmin.getPassword());
                return repository.save(existingAdmin);
            })
            .orElseThrow(() -> new RuntimeException("Admin not found!"));
    }

    // Delete Admin
    public String deleteAdmin(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Admin with ID " + id + " successfully deleted!";
        } else {
            throw new RuntimeException("Admin not found!");
        }
    }
}
