package com.java.util;

import com.java.domain.RailRoadMap;
import com.java.domain.Route;
import com.java.domain.Town;
import com.java.exception.RouteUniquenessException;
import com.java.exception.StartAndEndTownSamenessException;
import com.java.exception.WrongRailRoadGraphicException;

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

        Matcher matcher = getMatcherForRailRoadGraphic(railRoadGraphic);

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

        Matcher matcher = getMatcherForRailRoadGraphic(railRoadGraphic);
        Set<String> railRoadTrackSet = new TreeSet<>();

        while (matcher.find()) {
            String routeWithoutDistance = matcher.group().substring(0,2);
            if(!railRoadTrackSet.add(routeWithoutDistance))
                throw new RouteUniquenessException(ErrorCode.UNIQUENESS_FAILED);
        }

        return true;
    }


    public static boolean checkRouteSamenessInRailRoadGraphic(String railRoadGraphic) {
        Matcher matcher = getMatcherForRailRoadGraphic(railRoadGraphic);

        while (matcher.find()) {
            String routeWithoutDistance = matcher.group().substring(0,2);
            if(routeWithoutDistance.charAt(0) == routeWithoutDistance.charAt(1))
                throw new StartAndEndTownSamenessException(ErrorCode.SAMENESS_FAILED);
        }

        return true;

    }

    public static boolean checkCorrectnessOfRailRoadGraphic(String railRoadGraphic) {

        Set<String> splitSet = new HashSet<>(Arrays.asList(railRoadGraphic.trim().split("\\s*,\\s*")));

        Matcher matcher = getMatcherForRailRoadGraphic(railRoadGraphic);
        Set<String> matchSet = new HashSet<>();

        while (matcher.find()) {
            matchSet.add(matcher.group());
        }
        splitSet = symmetricDifference(splitSet, matchSet);
        if (!splitSet.isEmpty())
            throw new WrongRailRoadGraphicException(ErrorCode.RAILROAD_GRAPHIC_CORRECTNESS_FAILED);

        return true;
    }

    private static Matcher getMatcherForRailRoadGraphic(String railRoadGraphic){
        Pattern pattern = Pattern.compile("[ABCDE]{2}\\d+");
        return pattern.matcher(railRoadGraphic);
    }

    private static Set<String> symmetricDifference(Set<String> splitSet, Set<String> matchSet) {
        Set<String> result = new HashSet<>(splitSet);
        for (String element : matchSet) {
            if (!result.add(element.trim())) {
                result.remove(element);
            }
        }
        return result;
    }
}
