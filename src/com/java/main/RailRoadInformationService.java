package com.java.main;

import com.java.business.implementations.RailRoadServiceImpl;
import com.java.business.interfaces.IRailRoadService;
import com.java.domain.Town;
import com.java.util.RailRoadMapInitializer;

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

            IRailRoadService railRoadService = new RailRoadServiceImpl();
            System.out.println(railRoadService.getDistance(RailRoadMapInitializer.initializeRailRoadMap(railRoadGraphic), new Town[]{new Town("A"), new Town("B"), new Town("C")}));
            System.out.println(railRoadService.getDistance(RailRoadMapInitializer.initializeRailRoadMap(railRoadGraphic), new Town[]{new Town("A"), new Town("D")}));
            System.out.println(railRoadService.getDistance(RailRoadMapInitializer.initializeRailRoadMap(railRoadGraphic), new Town[]{new Town("A"), new Town("D"), new Town("C")}));
            System.out.println(railRoadService.getDistance(RailRoadMapInitializer.initializeRailRoadMap(railRoadGraphic), new Town[]{new Town("A"), new Town("E"), new Town("B"), new Town("C"), new Town("D")}));
            System.out.println(railRoadService.getDistance(RailRoadMapInitializer.initializeRailRoadMap(railRoadGraphic), new Town[]{new Town("A"), new Town("E"), new Town("D")}));

        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("File name does not correct.");
        }
    }
}
