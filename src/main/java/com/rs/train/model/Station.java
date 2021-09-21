package com.rs.train.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Station implements Comparable<Station>{
    private String code;
    private Integer distance;

    @Override
    public int compareTo(Station station) {
        return this.distance - station.getDistance();
    }
}
