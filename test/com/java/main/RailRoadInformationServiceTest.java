package com.java.main;

import com.java.business.implementations.RailRoadServiceImpl;
import com.java.business.interfaces.IRailRoadService;
import com.java.domain.RailRoadMap;
import com.java.domain.Town;
import com.java.exception.RouteUniquenessException;
import com.java.exception.StartAndEndTownSamenessException;
import com.java.exception.WrongRailRoadGraphicException;
import com.java.util.ErrorCode;
import com.java.util.RailRoadMapUtility;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;

/**
 * Created by Shamil on 10-Apr-18.
 */
public class RailRoadInformationServiceTest {

    private String railRoadGraphic;
    private IRailRoadService service;
    private RailRoadMap railRoadMap;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void createRailRoadService(){
        railRoadGraphic = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        railRoadMap = RailRoadMapUtility.initializeRailRoadMap(railRoadGraphic);
        service = new RailRoadServiceImpl(railRoadMap);
    }

    @Test
    public void trueWhenGivenDistanceMatchesToDistanceBetweenTwoTowns(){

        // Assert Part
        assertThat(9, equalTo(service.getDistance( new Town[]{new Town("A"), new Town("B"), new Town("C")})));
    }

    @Test
    public void throwExceptionWhenGivenRouteAppearMoreThanOneInRailRoadTracks(){

        // Arrange Part
        railRoadGraphic = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, AB3, AE7";
        thrown.expect(RouteUniquenessException.class);
        thrown.expect(hasProperty("code", is(ErrorCode.UNIQUENESS_FAILED)));

        // Act Part
        RailRoadMapUtility.checkRouteUniquenessInRailRoadGraphic(railRoadGraphic);
    }

    @Test
    public void trueWhenGivenCountMatchesTotalRoutesBetweenTwoTownsWithMax3Stops(){

        // Arrange Part
        Town startPoint = new Town("A");
        Town endPoint = new Town("C");

        // Act Part
        int count = 0;
        for (int i = 1; i <= 3; i++) {
            count += service.getTotalRoutesBetweenTwoTownsWithDefinedMaxStops(startPoint, endPoint, 0, i);
        }

        // Assert Part
        assertThat(3, equalTo(count));
    }

    @Test
    public void trueWhenGivenDistanceMatchesToShortDistanceBetweenTwoTowns(){

        // Arrange Part
        Town startPoint = new Town("A");
        Town endPoint = new Town("C");

        // Assert&Act Part
        assertThat(9, equalTo(service.getShortestDistanceBetweenTwoTowns(startPoint, endPoint)));
    }

    @Test
    public void trueThenGivenNumberMatchesToNumberOfRoutesBetweenTwoTownsWithDistanceLessThan30(){

        // Arrange Part
        Town startPoint = new Town("A");
        Town endPoint = new Town("D");

        // Assert&Act Part
        assertThat(6, equalTo(service.getTotalRoutesBetweenTwoTownsWithDistanceLessThan30(startPoint, endPoint, 0, 30)));
    }

    @Test
    public void throwExceptionWhenInGivenGraphicRouteStartAndEndTownIsTheSame(){

        // Arrange Part
        railRoadGraphic = "AA5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        thrown.expect(StartAndEndTownSamenessException.class);
        thrown.expect(hasProperty("code", is(ErrorCode.SAMENESS_FAILED)));

        // Act Part
        RailRoadMapUtility.checkRouteSamenessInRailRoadGraphic(railRoadGraphic);

    }

    @Test
    public void throwExceptionWhenRailRoadGraphicIsWrong(){

        // Arrange Part
        railRoadGraphic = "AAF5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        thrown.expect(WrongRailRoadGraphicException.class);
        thrown.expect(hasProperty("code", is(ErrorCode.RAILROAD_GRAPHIC_CORRECTNESS_FAILED)));

        // Act Part
        RailRoadMapUtility.checkCorrectnessOfRailRoadGraphic(railRoadGraphic);
    }
}