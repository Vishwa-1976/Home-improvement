package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Design;
import com.example.demo.Service.DesignService;

@RestController
@RequestMapping("/designs")
public class DesignController {

    @Autowired
    private DesignService service;

    // ✅ Add a new Design
    @PostMapping
    public List<Design> addDesign(@RequestBody List<Design> designs) {
        return service.addDesign(designs);
    }

    // ✅ Get all Designs
    @GetMapping
    public List<Design> getAllDesigns() {
        return service.getAllDesigns();
    }

    // ✅ Get Design by ID
    @GetMapping("/{id}")
    public Optional<Design> getDesignById(@PathVariable Long id) {
        return service.getDesignById(id);
    }

    // ✅ Update Design
    @PutMapping("/{id}")
    public Design updateDesign(@PathVariable Long id, @RequestBody Design updatedDesign) {
        return service.updateDesign(id, updatedDesign);
    }

    // ✅ Delete Design
    @DeleteMapping("/{id}")
    public String deleteDesign(@PathVariable Long id) {
        return service.deleteDesign(id);
    }

    // ✅ Find Designs by Title
    @GetMapping("/title/{title}")
    public List<Design> getDesignsByTitle(@PathVariable String title) {
        return service.getDesignsByTitle(title);
    }

    // ✅ Search Designs using keyword
    @GetMapping("/search")
    public List<Design> searchDesigns(@RequestParam String keyword) {
        return service.getDesignsByTitle(keyword); //http://localhost:8080/designs/search?title=modern

    }
}
