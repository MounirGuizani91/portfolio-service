package org.portfolio.model.converter;

import org.portfolio.model.dto.ProjectDto;
import org.portfolio.model.entity.Project;
import org.portfolio.model.enums.ProjectType;
import org.portfolio.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProjectConverter {

    @Autowired
    private MissionRepository missionRepository;

    public Project convertProjectDtoToProject(ProjectDto projectDto) {
        Project project = Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .build();

        if (Objects.nonNull(projectDto.getProjectType())) {
            project.setProjectType(ProjectType.valueOf(projectDto.getProjectType().toUpperCase()));
        }
        if (Objects.nonNull(projectDto.getMissionId())) {
            missionRepository.findById(projectDto.getMissionId()).ifPresent(project::setMission);
        }
        return project;
    }
}

