package org.portfolio.model.dto;

import lombok.Builder;
import org.portfolio.model.entity.Project;

import java.util.List;

@Builder
public class PagedProjectResponse {
    private List<Project> projects;
    private long totalElements;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}