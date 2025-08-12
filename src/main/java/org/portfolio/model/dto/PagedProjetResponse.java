package org.portfolio.model.dto;

import lombok.Builder;
import org.portfolio.model.entity.Projet;

import java.util.List;

@Builder
public class PagedProjetResponse {
    private List<Projet> projets;
    private long totalElements;

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}