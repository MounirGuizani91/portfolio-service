package org.portfolio.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import org.portfolio.model.enums.LevelType;

@Entity
public class AcademicLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank(message = "School is required")
    private String school;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Level type is required")
    private LevelType levelType;

    @NotBlank(message = "Country is required")
    private String country;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }
    public LevelType getLevelType() { return levelType; }
    public void setLevelType(LevelType levelType) { this.levelType = levelType; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}

