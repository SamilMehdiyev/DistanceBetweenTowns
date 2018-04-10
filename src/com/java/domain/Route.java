package com.java.domain;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Shamil on 10-Apr-18.
 */
public class Route{

    private Town startPoint;
    private Town endPoint;

    public Route(Town startPoint, Town endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Town getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Town startPoint) {
        this.startPoint = startPoint;
    }

    public Town getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Town endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (!getStartPoint().equals(route.getStartPoint())) return false;
        return getEndPoint().equals(route.getEndPoint());
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(getStartPoint().hashCode()
                + getEndPoint().hashCode());
    }
}
