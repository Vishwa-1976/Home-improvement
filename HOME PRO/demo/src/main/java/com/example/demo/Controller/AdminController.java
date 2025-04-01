package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Admin;
import com.example.demo.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    // ✅ Create Admin
    @PostMapping
    public List<Admin> createAdmin(@RequestBody List<Admin> admins) {
    return service.addAdmin(admins); 
    } 

    @GetMapping("/list")
    public List<Admin> getAllAdmins() {
        return service.getAllAdmins();
    }

    // ✅ Get Admin by Username
    @GetMapping("/username/{username}")
    public Optional<Admin> getAdminByUsername(@PathVariable String username) {
        return service.getAdminByUsername(username);
    }
    // ✅ Get Admin by ID
    @GetMapping("/{id}")
    public Optional<Admin> getAdminById(@PathVariable Long id) {
    return service.getAdminById(id);
    }
    // ✅ Get Admin by Email
    @GetMapping("/email/{email}")
    public Optional<Admin> getAdminByEmail(@PathVariable String email) {
        return service.getAdminByEmail(email);
    }

    // ✅ Get All Admins (Pagination & Sorting)
    @GetMapping("/all")
    public Page<Admin> getAllAdmins(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return service.getAllAdmins(PageRequest.of(page, size, sort));  
        //http://localhost:8080/admin/all?page=0&size=5&sortBy=username&direction=desc
    }

    // ✅ Update Admin
    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin updatedAdmin) {
        return service.updateAdmin(id, updatedAdmin);
    }

    // ✅ Delete Admin
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        return service.deleteAdmin(id);
    }
}
