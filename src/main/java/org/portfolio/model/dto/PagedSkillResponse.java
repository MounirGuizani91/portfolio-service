package org.portfolio.model.dto;

import lombok.Builder;
import org.portfolio.model.entity.Skill;
import java.util.List;

@Builder
public class PagedSkillResponse {
    private List<Skill> skills;
    private long totalElements;

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}

