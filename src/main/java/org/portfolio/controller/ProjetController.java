package org.portfolio.controller;

import org.portfolio.model.Projet;
import org.portfolio.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/projects")
public class ProjetController {

    @Autowired
    private ProjetRepository projetRepository;

    // Get all projects
    @GetMapping
    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    // Get a project by ID
    @GetMapping("/{id}")
    public Projet getProjetById(@PathVariable Long id) {
        return projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // Create a new project
    @PostMapping
    public Projet createProjet(@RequestBody Projet projet) {
        return projetRepository.save(projet);
    }

    // Update a project
    @PutMapping("/{id}")
    public Projet updateProjet(@PathVariable Long id, @RequestBody Projet projetDetails) {
        Projet projet = projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        projet.setNom(projetDetails.getNom());
        projet.setDescription(projetDetails.getDescription());
        projet.setDateDebut(projetDetails.getDateDebut());
        projet.setDateFin(projetDetails.getDateFin());
        return projetRepository.save(projet);
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetRepository.deleteById(id);
    }
}