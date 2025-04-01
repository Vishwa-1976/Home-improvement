package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Design {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    // Many Designs belong to one Contractor (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    @JsonBackReference
    private Contractor contractor;
}
