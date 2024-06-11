package org.example.entities.Enemy;

import java.util.List;

public class Path {
    private List<Point> waypoints;

    public Path(List<Point> waypoints) {
        this.waypoints = waypoints;
    }

    public List<Point> getWaypoints() {
        return waypoints;
    }
}
