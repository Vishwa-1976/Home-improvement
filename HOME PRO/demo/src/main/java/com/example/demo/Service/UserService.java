package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Admin;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Repositories.AdminRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    public UserService() {
    }

    // ✅ Get paginated users with sorting
    public Page<User> getUsersPaged(PageRequest pageable) {
        return userRepository.findAll(pageable);
    }

    // ✅ Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // ✅ Get users by name
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    // ✅ Get user by email
    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // ✅ Add users with Admin association
    public List<User> addUser(List<User> users) {
        for (User user : users) {
            if (user.getAdmin() != null) {
                Admin admin = adminRepository.findById(user.getAdmin().getId()).orElse(null);
                if (admin != null) {
                    user.setAdmin(admin);
                } else {
                    throw new RuntimeException("Admin with ID " + user.getAdmin().getId() + " not found");
                }
            }
        }
        return userRepository.saveAll(users);
    }

    // ✅ Update user
    public User updateUser(Long id, User newUserData) {
        return userRepository.findById(id).map(user -> {
            user.setName(newUserData.getName());
            user.setEmail(newUserData.getEmail());

            if (newUserData.getAdmin() != null) {
                Admin admin = adminRepository.findById(newUserData.getAdmin().getId()).orElse(null);
                if (admin != null) {
                    user.setAdmin(admin);
                } else {
                    throw new RuntimeException("Admin with ID " + newUserData.getAdmin().getId() + " not found");
                }
            }
            return userRepository.save(user);
        }).orElse(null);
    }

    // ✅ Delete users
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
