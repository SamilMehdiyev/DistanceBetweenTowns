package com.java.business.implementations;

import com.java.business.interfaces.IRailRoadService;
import com.java.domain.RailRoadMap;
import com.java.domain.Route;
import com.java.domain.Town;
import com.java.util.Constants;

import java.util.*;

/**
 * Created by Shamil on 10-Apr-18.
 */
public class RailRoadServiceImpl implements IRailRoadService {

    private RailRoadMap railRoadMap;

    public RailRoadServiceImpl(RailRoadMap railRoadMap) {
        this.railRoadMap = railRoadMap;
    }

    @Override
    public Integer getDistance(Town[] towns) {


        if ((railRoadMap == null) || (towns == null) || (towns.length == 0))
            throw new IllegalArgumentException("Invalid information defined");

        int distance = 0;
        Town lastVisitedTown = towns[0];

        for (Town town : towns) {
            if(lastVisitedTown.equals(town))
                continue;

            if(railRoadMap.getDistances().get(new Route(lastVisitedTown, town)) != null)
                distance += railRoadMap.getDistances().get(new Route(lastVisitedTown, town));
            else
                return -1;
            lastVisitedTown = town;
        }

        return distance;
    }

    @Override
    public Integer getTotalRoutesBetweenTwoTownsWithDefinedMaxStops(Town startPoint, Town endPoint, int currentStops, int maxStops) {

        int count = 0;

        if((currentStops == maxStops) && (startPoint.equals(endPoint)))
           return 1;

        if(currentStops > maxStops)
            return 0;

        for(Town town: railRoadMap.getRailRoadTracks().get(startPoint)){
            count += getTotalRoutesBetweenTwoTownsWithDefinedMaxStops(town, endPoint, currentStops + 1, maxStops);
        }

        return count;
    }

    @Override
    public Integer getShortestDistanceBetweenTwoTowns(Town startPoint, Town endPoint) {
        Map<Town, Integer> shortDistancesFromStartPointToOtherTowns = (Map<Town, Integer>) getShortestRouteInformationBetweenTwoTowns(startPoint, endPoint, Constants.DISTANCES);
        return shortDistancesFromStartPointToOtherTowns.get(endPoint);
    }

    @Override
    public Integer getTotalRoutesBetweenTwoTownsWithDistanceLessThan30(Town startPoint, Town endPoint, int currentDistance, int maxDistance) {

        int count = 0;

        if((currentDistance > 0) &&(currentDistance < maxDistance) && (startPoint.equals(endPoint)))
            count++;

        if(currentDistance >= maxDistance)
            return 0;

        for(Town town: railRoadMap.getRailRoadTracks().get(startPoint)){
            if(railRoadMap.getDistances().get(new Route(startPoint, town)) == null)
                continue;
            count += getTotalRoutesBetweenTwoTownsWithDistanceLessThan30(town, endPoint, currentDistance + railRoadMap.getDistances().get(new Route(startPoint, town)), maxDistance);
        }

        return count;
    }

    @Override
    public String getShortestRouteBetweenTwoTowns(Town startPoint, Town endPoint) {
        Map<Town, Town> parents = (Map<Town, Town>) getShortestRouteInformationBetweenTwoTowns(startPoint, endPoint, Constants.PARENTS);
        StringBuilder route = new StringBuilder(endPoint.getName() + "-");

        Town parent = new Town(endPoint.getName());
        while(true){
            route.append(parents.get(parent).getName());
            if(parents.get(parent).equals(startPoint))
                break;
            route.append("-");
            parent = parents.get(parent);
        }


        return route.reverse().toString();
    }

    private Object getShortestRouteInformationBetweenTwoTowns(Town startPoint, Town endPoint, String expectedInformation){

        Map<Town, Integer> shortDistancesFromStartPointToOtherTowns = new HashMap<>();
        Map<Town, Town> parents = new HashMap<>();

        Set<Town> visitedTowns = new HashSet<>();
        Queue<Town> townQueueForVisiting = new LinkedList<>();

        for(Town town: railRoadMap.getTowns()){
            shortDistancesFromStartPointToOtherTowns.put(town, Integer.MAX_VALUE);
            parents.put(town, null);
        }
        shortDistancesFromStartPointToOtherTowns.put(startPoint, 0);
        parents.put(startPoint, startPoint);
        townQueueForVisiting.add(startPoint);

        whileLoop:
        while (!townQueueForVisiting.isEmpty()){
            Town currentTown = townQueueForVisiting.poll();
            if(visitedTowns.contains(currentTown))
                continue;

            visitedTowns.add(currentTown);

            for(Town town: railRoadMap.getRailRoadTracks().get(currentTown)){
                int currentDistance = shortDistancesFromStartPointToOtherTowns.get(currentTown);
                int distanceBetweenCurrentTownAndTown = railRoadMap.getDistances().get(new Route(currentTown, town));
                if(((currentDistance + distanceBetweenCurrentTownAndTown) < shortDistancesFromStartPointToOtherTowns.get(town)) ||
                        (startPoint.equals(endPoint))){
                    shortDistancesFromStartPointToOtherTowns.put(town, currentDistance + distanceBetweenCurrentTownAndTown);
                    townQueueForVisiting.add(town);
                    parents.put(town, currentTown);
                }
                if(town.equals(startPoint))
                    break whileLoop;
            }
        }

        return expectedInformation.equals(Constants.DISTANCES) ? shortDistancesFromStartPointToOtherTowns :
                (expectedInformation.equals(Constants.PARENTS) ? parents : new IllegalArgumentException("Given parameter is wrong"));
    }
}
