package com.java.domain;

import java.util.Map;
import java.util.Set;

/**
 * Created by Shamil on 10-Apr-18.
 */
public class RailRoadMap {

    private Map<Town, Set<Town>> railRoadTracks;
    private Map<Route, Integer> distances;
    private Set<Town> towns;

    public RailRoadMap(Map<Town, Set<Town>> railRoadTracks, Map<Route, Integer> distances, Set<Town> towns) {
        this.railRoadTracks = railRoadTracks;
        this.distances = distances;
        this.towns = towns;
    }

    public Map<Town, Set<Town>> getRailRoadTracks() {
        return railRoadTracks;
    }

    public Map<Route, Integer> getDistances() {
        return distances;
    }

    public Set<Town> getTowns() {
        return towns;
    }
}
