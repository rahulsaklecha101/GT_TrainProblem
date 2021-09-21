package com.rs.train.service;

import com.rs.train.model.Station;
import com.rs.train.model.Train;

import java.util.HashMap;
import java.util.Map;

public enum RouteService {

    INSTANCE;

    private Map<String, Map> routes = new HashMap<>();

    RouteService(){
        //prepare route A
        Map<String, Integer> routeA = new HashMap<>();
        routeA.put("CHN", 0);
        routeA.put("SLM", 350);
        routeA.put("BLR", 550);
        routeA.put("KRN", 900);
        routeA.put("HYB", 1200);
        routeA.put("NGP", 1600);
        routeA.put("ITJ", 1900);
        routeA.put("BPL", 2000);
        routeA.put("AGA", 2500);
        routeA.put("NDL", 2700);
        routes.put("A", routeA);

        //prepare route B
        Map<String, Integer> routeB = new HashMap<>();
        routeB.put("TVC", 0);
        routeB.put("SRR", 300);
        routeB.put("MAQ", 600);
        routeB.put("MAO", 1000);
        routeB.put("PNE", 1400);
        routeB.put("HYB", 2000);
        routeB.put("NGP", 2400);
        routeB.put("ITJ", 2700);
        routeB.put("BPL", 2800);
        routeB.put("PTA", 3800);
        routeB.put("NJP", 4200);
        routeB.put("GHY", 4700);
        routes.put("B", routeB);
    }

    public Train prepareTrain(String[] halts) {
        String routeCode = halts[0].substring(6);
        System.out.println("Preparing train for " + routeCode);
        Map<String, Integer> trainRoute = routes.get(routeCode);
        Train train = new Train(routeCode);
        if (trainRoute != null) {
            for (int index = 2; index < halts.length; index++) {
                String halt = halts[index];
                train.addStation(halt, trainRoute.get(halt));
            }
        }
        return train;
    }

    public Station getStation(String routeCode, String stationCode){
        Map<String, Integer> trainRoute = routes.get(routeCode.substring(6));
        if (trainRoute != null) {
            return new Station(stationCode, trainRoute.get(stationCode));
        }
        return null;
    }
}
