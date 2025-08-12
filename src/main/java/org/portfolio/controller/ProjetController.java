package org.portfolio.controller;

import org.portfolio.model.dto.PagedProjetResponse;
import org.portfolio.model.entity.Projet;
import org.portfolio.repository.ProjetRepository;
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
    private ProjetRepository projetRepository;

    @GetMapping("/paged")
    public ResponseEntity<PagedProjetResponse> getProjetsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Projet> result = projetRepository.findAll(pageable);
        return ResponseEntity.ok(PagedProjetResponse.builder()
                .projets(result.getContent())
                .totalElements(result.getTotalElements())
                .build());
    }

    @GetMapping
    public ResponseEntity<List<Projet>> getAllProjets() {
        List<Projet> projets = projetRepository.findAll();
        return ResponseEntity.ok(projets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> getProjetById(@PathVariable Long id) {
        return projetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
        Projet saved = projetRepository.save(projet);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projet> updateProjet(@PathVariable Long id, @RequestBody Projet projetDetails) {
        return projetRepository.findById(id)
                .map(projet -> {
                    projet.setNom(projetDetails.getNom());
                    projet.setDescription(projetDetails.getDescription());
                    projet.setDateDebut(projetDetails.getDateDebut());
                    projet.setDateFin(projetDetails.getDateFin());
                    Projet updated = projetRepository.save(projet);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        if (projetRepository.existsById(id)) {
            projetRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}