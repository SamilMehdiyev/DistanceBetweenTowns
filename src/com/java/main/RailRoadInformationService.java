package com.java.main;

import com.java.business.implementations.RailRoadServiceImpl;
import com.java.business.interfaces.IRailRoadService;
import com.java.domain.RailRoadMap;
import com.java.domain.Town;
import com.java.util.ErrorCode;
import com.java.util.RailRoadMapUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Shamil on 10-Apr-18.
 */
public class RailRoadInformationService {
    public static void main(String[] args) {
        try {

            Scanner scanner = new Scanner(new FileInputStream("input.txt"));
            String railRoadGraphic = scanner.nextLine();
            scanner.close();

            if (RailRoadMapUtility.checkCorrectnessOfRailRoadGraphic(railRoadGraphic)) {

                if (RailRoadMapUtility.checkRouteUniquenessInRailRoadGraphic(railRoadGraphic) &&
                        RailRoadMapUtility.checkRouteSamenessInRailRoadGraphic(railRoadGraphic)) {

                    RailRoadMap railRoadMap = RailRoadMapUtility.initializeRailRoadMap(railRoadGraphic);
                    IRailRoadService railRoadService = new RailRoadServiceImpl(railRoadMap);

                    System.out.println("Output #1: " + getDistanceOfTrajectoryTownA_TownB_TownC(railRoadService));
                    System.out.println("Output #2: " + getDistanceOfTrajectoryTownA_TownD(railRoadService));
                    System.out.println("Output #3: " + getDistanceOfTrajectoryTownA_TownD_TownC(railRoadService));
                    System.out.println("Output #4: " + getDistanceOfTrajectoryTownA_TownE_TownB_TownC_TownD(railRoadService));
                    System.out.println("Output #5: " + getDistanceOfTrajectoryTownA_TownE_TownD(railRoadService));
                    System.out.println("Output #6: " + getPossibleRouteCountBetweenTownAAndTownCWithMax3Stops(railRoadService));
                    System.out.println("Output #7: " + getTotalRouteCountBetweenTownAAndTownCWithExactly4Stops(railRoadService));
                    System.out.println("Output #8: " + getShortestDistanceBetweenTownAAndTownC(railRoadService));
                    System.out.println("Output #9: " + getShortestDistanceBetweenTownBAndTownB(railRoadService));
                    System.out.println("Output #10: " + getTotalRoutesBetweenTownCAndTownCWithDistanceLessThan30(railRoadService));

                }
            }
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("File name does not correct.");
        }
    }

    private static String getDistanceOfTrajectoryTownA_TownB_TownC(IRailRoadService railRoadService) {
        int distance = railRoadService.getDistance(new Town[]{new Town("A"), new Town("B"), new Town("C")});
        return distance >=0 ? String.valueOf(distance) : ErrorCode.NO_SUCH_ROUTE.getDescription();
    }

    private static String getDistanceOfTrajectoryTownA_TownD(IRailRoadService railRoadService) {
        int distance = railRoadService.getDistance(new Town[]{new Town("A"), new Town("D")});
        return distance >=0 ? String.valueOf(distance) : ErrorCode.NO_SUCH_ROUTE.getDescription();
    }

    private static String getDistanceOfTrajectoryTownA_TownD_TownC(IRailRoadService railRoadService) {
        int distance = railRoadService.getDistance(new Town[]{new Town("A"), new Town("D"), new Town("C")});
        return distance >=0 ? String.valueOf(distance) : ErrorCode.NO_SUCH_ROUTE.getDescription();
    }

    private static String getDistanceOfTrajectoryTownA_TownE_TownB_TownC_TownD(IRailRoadService railRoadService) {
        int distance = railRoadService.getDistance(new Town[]{new Town("A"), new Town("E"), new Town("B"), new Town("C"), new Town("D")});
        return distance >=0 ? String.valueOf(distance) : ErrorCode.NO_SUCH_ROUTE.getDescription();
    }

    private static String getDistanceOfTrajectoryTownA_TownE_TownD(IRailRoadService railRoadService) {
        int distance = railRoadService.getDistance(new Town[]{new Town("A"), new Town("E"), new Town("D")});
        return distance >=0 ? String.valueOf(distance) : ErrorCode.NO_SUCH_ROUTE.getDescription();
    }

    private static String getPossibleRouteCountBetweenTownAAndTownCWithMax3Stops(IRailRoadService railRoadService) {
        int totalRouteCountBetweenTownAAndTownCWithMax3Stops = 0;
        for (int i = 1; i <= 3; i++) {
            totalRouteCountBetweenTownAAndTownCWithMax3Stops += railRoadService.getTotalRoutesBetweenTwoTownsWithDefinedMaxStops(new Town("C"), new Town("C"), 0, i);
        }
        return String.valueOf(totalRouteCountBetweenTownAAndTownCWithMax3Stops);
    }

    private static String getTotalRouteCountBetweenTownAAndTownCWithExactly4Stops(IRailRoadService railRoadService) {
        return String.valueOf(railRoadService.getTotalRoutesBetweenTwoTownsWithDefinedMaxStops(new Town("A"), new Town("C"), 0, 4));
    }

    private static String getShortestDistanceBetweenTownAAndTownC(IRailRoadService railRoadService) {
        return String.valueOf(railRoadService.getShortestDistanceBetweenTwoTowns(new Town("A"), new Town("C")));
    }

    private static String getShortestDistanceBetweenTownBAndTownB(IRailRoadService railRoadService) {
        return String.valueOf(railRoadService.getShortestDistanceBetweenTwoTowns(new Town("B"), new Town("B")));
    }

    private static String getTotalRoutesBetweenTownCAndTownCWithDistanceLessThan30(IRailRoadService railRoadService) {
        return String.valueOf(railRoadService.getTotalRoutesBetweenTwoTownsWithDistanceLessThan30(new Town("C"), new Town("C"), 0, 30));
    }
}
