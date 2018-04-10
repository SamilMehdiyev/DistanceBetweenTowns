package com.java.business.implementations;

import com.java.business.interfaces.IRailRoadService;
import com.java.domain.RailRoadMap;
import com.java.domain.Route;
import com.java.domain.Town;

/**
 * Created by Shamil on 10-Apr-18.
 */
public class RailRoadServiceImpl implements IRailRoadService {

    @Override
    public Integer getDistance(RailRoadMap railRoadMap, Town[] towns) {


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
}
