package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Design;
import com.example.demo.Repositories.DesignRepository;

@Service
public class DesignService {
    @Autowired
    private DesignRepository repository;

    // ✅ Add a new Design
    public List<Design> addDesign(List<Design> design) {
        return repository.saveAll(design);
    }

    // ✅ Get All Designs
    public List<Design> getAllDesigns() {
        return repository.findAll();
    }

    // ✅ Get Design by ID
    public Optional<Design> getDesignById(Long id) {
        return repository.findById(id);
    }

    // ✅ Update Design
    public Design updateDesign(Long id, Design updatedDesign) {
        return repository.findById(id)
            .map(existingDesign -> {
                existingDesign.setTitle(updatedDesign.getTitle());
                existingDesign.setDescription(updatedDesign.getDescription());
                return repository.save(existingDesign);
            }).orElseThrow(() -> new RuntimeException("Design not found!"));
    }

    // ✅ Delete Design
    public String deleteDesign(Long id) {
        repository.deleteById(id);
        return "Design ID " + id + " deleted successfully!";
    }

    // ✅ Find by Title (Custom Query)
    public List<Design> getDesignsByTitle(String title) {
        return repository.findByTitle(title);
    }
    
}
