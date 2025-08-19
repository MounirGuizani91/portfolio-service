package org.portfolio.controller;

import org.portfolio.model.dto.PagedAcademicLevelResponse;
import org.portfolio.model.entity.AcademicLevel;
import org.portfolio.repository.AcademicLevelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic-levels")
public class AcademicLevelController {

    private final AcademicLevelRepository academicLevelRepository;

    public AcademicLevelController(AcademicLevelRepository academicLevelRepository) {
        this.academicLevelRepository = academicLevelRepository;
    }

    @GetMapping("/paged")
    public ResponseEntity<PagedAcademicLevelResponse> getAcademicLevelsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<AcademicLevel> result = academicLevelRepository.findAll(pageable);
        return ResponseEntity.ok(PagedAcademicLevelResponse.builder()
                .academicLevels(result.getContent())
                .totalElements(result.getTotalElements())
                .build());
    }

    @GetMapping
    public ResponseEntity<List<AcademicLevel>> getAllAcademicLevels() {
        List<AcademicLevel> levels = academicLevelRepository.findAll();
        return ResponseEntity.ok(levels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicLevel> getAcademicLevelById(@PathVariable Long id) {
        return academicLevelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<AcademicLevel> createAcademicLevel(@RequestBody AcademicLevel academicLevel) {
        AcademicLevel saved = academicLevelRepository.save(academicLevel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicLevel> updateAcademicLevel(@PathVariable Long id, @RequestBody AcademicLevel details) {
        return academicLevelRepository.findById(id)
                .map(level -> {
                    level.setName(details.getName());
                    level.setDescription(details.getDescription());
                    level.setSchool(details.getSchool());
                    level.setLevelType(details.getLevelType());
                    level.setCountry(details.getCountry());
                    AcademicLevel updated = academicLevelRepository.save(level);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcademicLevel(@PathVariable Long id) {
        if (academicLevelRepository.existsById(id)) {
            academicLevelRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
