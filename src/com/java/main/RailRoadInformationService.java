package com.java.main;

import com.java.business.implementations.RailRoadServiceImpl;
import com.java.business.interfaces.IRailRoadService;
import com.java.domain.RailRoadMap;
import com.java.domain.Town;
import com.java.exception.RouteUniquenessException;
import com.java.util.RailRoadMapUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Shamil on 10-Apr-18.
 */
public class RailRoadInformationService {
    public static void main(String[] args) throws RouteUniquenessException {
        try {

            Scanner scanner = new Scanner(new FileInputStream("input.txt"));
            String railRoadGraphic = scanner.nextLine();
            scanner.close();

            if (RailRoadMapUtility.checkRouteUniquenessInRailRoadGraphic(railRoadGraphic)) {

                RailRoadMap railRoadMap = RailRoadMapUtility.initializeRailRoadMap(railRoadGraphic);
                IRailRoadService railRoadService = new RailRoadServiceImpl(railRoadMap);

                System.out.println(railRoadService.getDistance(new Town[]{new Town("A"), new Town("B"), new Town("C")}));
                System.out.println(railRoadService.getDistance(new Town[]{new Town("A"), new Town("D")}));
                System.out.println(railRoadService.getDistance(new Town[]{new Town("A"), new Town("D"), new Town("C")}));
                System.out.println(railRoadService.getDistance(new Town[]{new Town("A"), new Town("E"), new Town("B"), new Town("C"), new Town("D")}));
                System.out.println(railRoadService.getDistance(new Town[]{new Town("A"), new Town("E"), new Town("D")}));

                int totalRouteCountBetweenTownAAndTownCWithMax3Stops = 0;

                for (int i = 1; i <= 3; i++) {
                    totalRouteCountBetweenTownAAndTownCWithMax3Stops += railRoadService.getTotalRoutesBetweenTwoTownsWithDefinedMaxStops(new Town("C"), new Town("C"),0, i);
                }
                System.out.println(totalRouteCountBetweenTownAAndTownCWithMax3Stops);
                System.out.println(railRoadService.getTotalRoutesBetweenTwoTownsWithDefinedMaxStops(new Town("A"), new Town("C"),0, 4));
                System.out.println(railRoadService.getShortestDistanceBetweenTwoTowns(new Town("A"), new Town("C")));
                System.out.println(railRoadService.getShortestDistanceBetweenTwoTowns(new Town("B"), new Town("B")));
                System.out.println(railRoadService.getTotalRoutesBetweenTwoTownsWithDistanceLessThan30(new Town("C"), new Town("C"), 0, 30));

            }
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("File name does not correct.");
        }
    }
}
