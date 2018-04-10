package com.java.business.interfaces;

import com.java.domain.RailRoadMap;
import com.java.domain.Town;

/**
 * Created by Shamil on 10-Apr-18.
 */
public interface IRailRoadService {
    Integer getDistance(RailRoadMap railRoadMap, Town[] town);
}
