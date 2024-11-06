package com.phamcongvinh.testusser.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddRocksDTO {
    private Set<Long> rockIds;

    public Set<Long> getRockIds() {
        return rockIds;
    }

    public void setRockIds(Set<Long> rockIds) {
        this.rockIds = rockIds;
    }
}
