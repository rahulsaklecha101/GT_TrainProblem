package com.rs.train.model;

import lombok.Data;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
public class Train {

    private String routeCode;
    private TreeSet<Station> ts = new TreeSet();

    public Train(String routeCode){
        this.routeCode = routeCode;
    }

    public boolean addStation(String code, Integer distance){
        return this.ts.add(new Station(code, distance));
    }

    public boolean addStation(Station station){
        return this.ts.add(station);
    }

    public Station getNextStation(Station station){
        if(station == null){
            return this.ts.first();
        }
        return this.ts.ceiling(station);
    }

    public Station getPreviousStation(Station station){
        if(station == null){
            return this.ts.first();
        }
        return this.ts.floor(station);
    }

    public SortedSet<Station> getStations(String code){
        Optional<Station> opt = this.ts.stream().filter(s -> s.getCode().equals(code)).findAny();
        return getStations(opt.isPresent() ? opt.get() : null, null);
    }

    public SortedSet<Station> getStations(Station from, Station to){
        if(from == null){
            return (SortedSet<Station>) this.ts.clone();
        }

        if(to == null){
            return this.ts.tailSet(from);
        }
        return this.ts.subSet(from, to);
    }
}
