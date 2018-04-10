package com.java.main;

import com.java.business.implementations.RailRoadServiceImpl;
import com.java.business.interfaces.IRailRoadService;
import com.java.domain.RailRoadMap;
import com.java.domain.Route;
import com.java.domain.Town;
import com.java.util.RailRoadMapInitializer;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Shamil on 10-Apr-18.
 */
public class RailRoadInformationServiceTest {

    private IRailRoadService service;
    private RailRoadMap railRoadMap;


    @Before
    public void createRailRoadService(){
        service = new RailRoadServiceImpl();
        railRoadMap = initialize();
    }

    @Test
    public void trueWhenGivenDistanceMatchesToDistanceBetweenTwoTowns(){
        assertThat(9, equalTo(service.getDistance(railRoadMap, new Town[]{new Town("A"), new Town("B"), new Town("C")})));
    }

    private static RailRoadMap initialize(){

        /*Map<Town, Set<Route>> railRoadTracks = new HashMap<>();
        Map<Route, Integer> distances = new HashMap<>();
        Set<Town> towns = new HashSet<>();

        Town townA = new Town("A");
        Town townB = new Town("B");
        Town townC = new Town("C");
        Town townD = new Town("D");
        Town townE = new Town("E");

        Set<Route> townARoutes = new HashSet<>();
        townARoutes.add(new Route(townA, townB));
        townARoutes.add(new Route(townA, townD));
        townARoutes.add(new Route(townA, townE));

        Set<Route> townBRoutes = new HashSet<>();
        townBRoutes.add(new Route(townB, townC));

        Set<Route> townCRoutes = new HashSet<>();
        townCRoutes.add(new Route(townC, townD));
        townCRoutes.add(new Route(townC, townE));

        Set<Route> townDRoutes = new HashSet<>();
        townDRoutes.add(new Route(townD, townC));
        townDRoutes.add(new Route(townD, townE));

        Set<Route> townERoutes = new HashSet<>();
        townERoutes.add(new Route(townE, townB));

        railRoadTracks.put(townA, townARoutes);
        railRoadTracks.put(townB, townBRoutes);
        railRoadTracks.put(townC, townCRoutes);
        railRoadTracks.put(townD, townDRoutes);
        railRoadTracks.put(townE, townERoutes);

        towns.add(townA);
        towns.add(townB);
        towns.add(townC);
        towns.add(townD);
        towns.add(townE);

        distances.put(new Route(townA, townB), 5);
        distances.put(new Route(townA, townD), 5);
        distances.put(new Route(townA, townE), 7);

        distances.put(new Route(townB, townC), 4);

        distances.put(new Route(townC, townD), 8);
        distances.put(new Route(townC, townE), 2);

        distances.put(new Route(townD, townC), 8);
        distances.put(new Route(townD, townE), 6);

        distances.put(new Route(townE, townB), 3);*/

        return RailRoadMapInitializer.initializeRailRoadMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    }

}