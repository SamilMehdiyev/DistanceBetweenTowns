package com.java.util;

import com.java.domain.RailRoadMap;
import com.java.domain.Route;
import com.java.domain.Town;
import com.java.exception.RouteUniquenessException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shamil on 11-Apr-18.
 */
public abstract class RailRoadMapUtility {

    public static RailRoadMap initializeRailRoadMap(String railRoadGraphic) {

        Map<Town, Set<Town>> railRoadTracks = new HashMap<>();
        Map<Route, Integer> distances = new HashMap<>();
        Set<Town> towns = new HashSet<>();

        String regex = "[ABCDE]+\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(railRoadGraphic);

        while (matcher.find()) {

            String routeInformation = matcher.group();

            Town startPoint = new Town(String.valueOf(routeInformation.charAt(0)));
            Town endPoint = new Town(String.valueOf(routeInformation.charAt(1)));
            int distance = Integer.parseInt(routeInformation.substring(2));

            Set<Town> routesFromStartPoint = railRoadTracks.get(startPoint) != null ? railRoadTracks.get(startPoint) : new HashSet<>();
            routesFromStartPoint.add(endPoint);

            railRoadTracks.put(startPoint, routesFromStartPoint);
            towns.add(startPoint);

            distances.put(new Route(startPoint, endPoint), distance);
        }

        return new RailRoadMap(railRoadTracks, distances, towns);
    }

    public static boolean checkRouteUniquenessInRailRoadGraphic(String railRoadGraphic) throws RouteUniquenessException {

        String regex = "[ABCDE]+\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(railRoadGraphic);

        Set<String> railRoadTrackSet = new TreeSet<>();

        while (matcher.find()) {
            String routeWithoutDistance = matcher.group().substring(0,2);
            if(!railRoadTrackSet.add(routeWithoutDistance))
                throw new RouteUniquenessException(ErrorCode.UNIQUENESS_FAILED);
        }

        return true;
    }
}
