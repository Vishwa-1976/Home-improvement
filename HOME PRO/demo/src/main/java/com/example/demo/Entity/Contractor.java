package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate ID
    private Long id;
    
    private String name;
    private String specialization;
    private Double rating;

    // One Contractor can manage multiple Designs (One-to-Many)
    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Design> designs;

    // One Contractor can receive multiple Payments (One-to-Many)
    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Money> payments;
}
