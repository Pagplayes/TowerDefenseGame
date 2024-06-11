package org.example.entities.utils;

import org.example.entities.Enemy.Enemy;
import org.example.entities.Enemy.Point;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Spawner {
    private List<GameObject> enemies;
    private List<Point> path;
    private int spawnInterval;
    private int maxEnemies;
    private Timer timer;
    private int spawnedEnemies;
    private int enemyHealth;
    private int enemySpeed;


    public Spawner(List<GameObject> enemies, List<Point> path, int spawnInterval, int maxEnemies,int enemyHealth,int enemySpeed) {
        this.enemies = enemies;
        this.path = path;
        this.spawnInterval = spawnInterval;
        this.maxEnemies = maxEnemies;
        this.timer = new Timer();
        this.spawnedEnemies = 0;
        this.enemyHealth = enemyHealth;
        this.enemySpeed = enemySpeed;
    }

    public void start() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        spawnedEnemies = 0;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (spawnedEnemies < maxEnemies) {
                    spawnEnemy();
                } else {
                    stop();
                }
            }
        }, 0, spawnInterval);
    }

    public void stop() {
        timer.cancel();
    }

    private void spawnEnemy() {
        enemies.add(new Enemy(path.get(0).getX(), path.get(0).getY(), 40, 40, enemySpeed, path, enemyHealth)); // Example health value
        spawnedEnemies++;
    }

    public boolean isRunning() {
        return timer != null;
    }


}
