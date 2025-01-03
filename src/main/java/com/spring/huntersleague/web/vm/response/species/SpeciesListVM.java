package com.spring.huntersleague.web.vm.response.species;

import com.spring.huntersleague.domain.Species;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SpeciesListVM {
    private List<Species> species;
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;

    public SpeciesListVM(List<Species> species, int totalPages, long totalElements, int pageNumber, int pageSize) {
        this.species = species;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}