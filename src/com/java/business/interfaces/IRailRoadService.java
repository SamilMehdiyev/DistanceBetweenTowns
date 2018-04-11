package com.java.business.interfaces;

import com.java.domain.Town;

/**
 * Created by Shamil on 10-Apr-18.
 */
public interface IRailRoadService {

    Integer getDistance(Town[] town);

    Integer getTotalRoutesBetweenTwoTownsWithDefinedMaxStops(Town startPoint, Town endPoint, int currentStops, int maxStops);

    Integer getShortestDistanceBetweenTwoTowns(Town startPoint, Town endPoint);

    Integer getTotalRoutesBetweenTwoTownsWithDistanceLessThan30(Town startPoint, Town endPoint, int currentDistance, int maxDistance);
}
