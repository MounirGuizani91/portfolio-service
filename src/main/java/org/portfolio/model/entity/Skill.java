package org.portfolio.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.portfolio.model.enums.SkillType;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private String version;

    @Min(value = 1, message = "Level must be at least 1")
    @Max(value = 5, message = "Level cannot exceed 5")
    private int level;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Skill type is required")
    private SkillType skillType;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public SkillType getSkillType() { return skillType; }
    public void setSkillType(SkillType skillType) { this.skillType = skillType; }
}

