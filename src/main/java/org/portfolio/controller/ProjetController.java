package org.portfolio.controller;

import org.portfolio.model.converter.ProjectConverter;
import org.portfolio.model.dto.PagedProjectResponse;
import org.portfolio.model.dto.ProjectDto;
import org.portfolio.model.entity.Project;
import org.portfolio.repository.MissionRepository;
import org.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjetController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private ProjectConverter projectConverter;

    public ProjetController(ProjectRepository projectRepository, MissionRepository missionRepository) {
        this.projectRepository = projectRepository;
        this.missionRepository = missionRepository;
    }

    @GetMapping("/paged")
    public ResponseEntity<PagedProjectResponse> getProjetsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Project> result = projectRepository.findAll(pageable);
        return ResponseEntity.ok(PagedProjectResponse.builder()
                .projects(result.getContent())
                .totalElements(result.getTotalElements())
                .build());
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjets() {
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjetById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Project> createProjet(@RequestBody ProjectDto projectdto) {
        Project project = projectConverter.convertProjectDtoToProject(projectdto);
        Project saved = projectRepository.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProjet(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    Project updatedProject = projectConverter.convertProjectDtoToProject(projectDto);
                    updatedProject.setId(existingProject.getId()); // S'assurer de garder le même ID
                    Project saved = projectRepository.save(updatedProject);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}