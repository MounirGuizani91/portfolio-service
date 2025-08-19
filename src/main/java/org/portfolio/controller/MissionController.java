package org.portfolio.controller;

import org.portfolio.model.dto.PagedMissionResponse;
import org.portfolio.model.dto.PagedProjectResponse;
import org.portfolio.model.entity.Mission;
import org.portfolio.model.entity.Project;
import org.portfolio.repository.MissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {

    private final MissionRepository missionRepository;

    public MissionController(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @GetMapping("/paged")
    public ResponseEntity<PagedMissionResponse> getMissionsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Mission> result = missionRepository.findAll(pageable);
        return ResponseEntity.ok(PagedMissionResponse.builder()
                .missions(result.getContent())
                .totalElements(result.getTotalElements())
                .build());
    }

    @GetMapping
    public ResponseEntity<List<Mission>> getAllMissions() {
        List<Mission> missions = missionRepository.findAll();
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMissionById(@PathVariable Long id) {
        return missionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody Mission mission) {
        Mission saved = missionRepository.save(mission);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mission> updateMission(@PathVariable Long id, @RequestBody Mission missionDetails) {
        return missionRepository.findById(id)
                .map(mission -> {
                    mission.setName(missionDetails.getName());
                    mission.setResponsibility(missionDetails.getResponsibility());
                    mission.setStartDate(missionDetails.getStartDate());
                    mission.setEndDate(missionDetails.getEndDate());
                    mission.setClientName(missionDetails.getClientName());
                    Mission updated = missionRepository.save(mission);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        if (missionRepository.existsById(id)) {
            missionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

