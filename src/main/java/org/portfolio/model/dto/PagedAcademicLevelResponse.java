package org.portfolio.model.dto;

import lombok.Builder;
import org.portfolio.model.entity.AcademicLevel;

import java.util.List;

@Builder
public class PagedAcademicLevelResponse {
    private List<AcademicLevel> academicLevels;
    private long totalElements;

    public List<AcademicLevel> getAcademicLevels() {
        return academicLevels;
    }

    public void setAcademicLevels(List<AcademicLevel> academicLevels) {
        this.academicLevels = academicLevels;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}

