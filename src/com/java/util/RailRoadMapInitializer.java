package com.java.util;

import com.java.domain.RailRoadMap;
import com.java.domain.Route;
import com.java.domain.Town;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shamil on 11-Apr-18.
 */
public abstract class RailRoadMapInitializer {

    public static RailRoadMap initializeRailRoadMap(String railRoadInformation) {

        Map<Town, Set<Route>> railRoadTracks = new HashMap<>();
        Map<Route, Integer> distances = new HashMap<>();
        Set<Town> towns = new HashSet<>();

        String regex = "[ABCDE]+\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(railRoadInformation);

        while (matcher.find()) {

            String routeInformation = matcher.group();

            Town startPoint = new Town(String.valueOf(routeInformation.charAt(0)));
            Town endPoint = new Town(String.valueOf(routeInformation.charAt(1)));
            int distance = Integer.parseInt(routeInformation.substring(2));

            Set<Route> routesFromStartPoint = railRoadTracks.get(startPoint) != null ? railRoadTracks.get(startPoint) : new HashSet<>();
            routesFromStartPoint.add(new Route(startPoint, endPoint));

            railRoadTracks.put(startPoint, routesFromStartPoint);
            towns.add(startPoint);

            distances.put(new Route(startPoint, endPoint), distance);
        }

        return new RailRoadMap(railRoadTracks, distances, towns);
    }

}
