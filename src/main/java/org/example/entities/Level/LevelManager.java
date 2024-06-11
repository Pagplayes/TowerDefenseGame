package org.example.entities.Level;

import org.example.entities.Enemy.Goal;
import org.example.entities.Enemy.Point;

import java.util.List;
import java.util.ArrayList;

public class LevelManager {
    private List<Level> levels;
    private int currentLevelIndex;

    public LevelManager() {
        levels = new ArrayList<>();
        currentLevelIndex = 0;
        loadLevels();
    }

    private void loadLevels() {
        // Define Level 1
        List<Point> path1 = new ArrayList<>();
        path1.add(new Point(0, 200));
        path1.add(new Point(780, 200));
        Goal goal1 = new Goal(780, 180, 20, 40);
        levels.add(new Level(path1, goal1, 10));

        // Define Level 2
        List<Point> path2 = new ArrayList<>();
        path2.add(new Point(0, 100));
        path2.add(new Point(400, 100));
        path2.add(new Point(400, 300));
        path2.add(new Point(780, 300));
        Goal goal2 = new Goal(780, 280, 20, 40);
        levels.add(new Level(path2, goal2, 15));

        // Add more levels as needed
    }

    public Level getCurrentLevel() {
        return levels.get(currentLevelIndex);
    }

    public void nextLevel() {
        currentLevelIndex = (currentLevelIndex + 1) % levels.size();
    }

    public boolean hasNextLevel() {
        return currentLevelIndex < levels.size() - 1;
    }
}