package org.example.entities.Level;

import org.example.entities.Enemy.Goal;
import org.example.entities.Enemy.Point;

import java.util.List;

public class Level {
    private List<Point> path;
    private Goal goal;
    private int maxEnemies;

    public Level(List<Point> path, Goal goal, int maxEnemies) {
        this.path = path;
        this.goal = goal;
        this.maxEnemies = maxEnemies;
    }

    public List<Point> getPath() {
        return path;
    }

    public Goal getGoal() {
        return goal;
    }

    public int getMaxEnemies() {
        return maxEnemies;
    }
}
