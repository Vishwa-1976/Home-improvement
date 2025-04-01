package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    public UserController() {}

    // ✅ Get paginated users with sorting (Pagination + Sorting Together)
    @GetMapping
    public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "false") boolean descending) {
        
        Sort sort = descending ? Sort.by("name").descending() : Sort.by("name").ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return service.getUsersPaged(pageable);
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // ✅ Get users by name (Pagination + Sorting Together)
    @GetMapping("/search/name")
    public List<User> getUsersByName(@RequestParam String name) {
        return service.getUsersByName(name);  //http://localhost:8080/users?page=0&size=2&descending=true // http://localhost:8080/users/search/name?name=Alice
    }

    // ✅ Get user by email
    @GetMapping("/search/email")
    public List<User> getUsersByEmail(@RequestParam String email) {
    return service.getUsersByEmail(email);  //http://localhost:8080/users/search/email?email=bob@example.com
    }


    // ✅ Add new users
    @PostMapping
    public List<User> createUser(@RequestBody List<User> users) {
        return service.addUser(users);
    }

    // ✅ Update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUserData) {
        return service.updateUser(id, newUserData);
    }

    // ✅ Delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "User id deleted successfully";
    }
}

