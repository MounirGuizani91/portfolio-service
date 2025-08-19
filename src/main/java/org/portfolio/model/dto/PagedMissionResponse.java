package org.portfolio.model.dto;

import lombok.Builder;
import org.portfolio.model.entity.Mission;

import java.util.List;

@Builder
public class PagedMissionResponse {
    private List<Mission> missions;
    private long totalElements;

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}