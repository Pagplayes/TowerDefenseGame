package org.example.entities.Enemy;

import org.example.entities.utils.GameObject;

import java.util.List;
import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends GameObject {

    private int speed;
    private List<Point> path;
    private int currentWaypoint;
    private int health;

    public Enemy(int x, int y, int width, int height,int speed,List<Point> path,int health) {
        super(x, y, width, height);
        this.speed = speed;
        this.path = path;
        this.currentWaypoint = 0;
        this.health = health;
    }

    @Override
    public void update() {
        if (currentWaypoint < path.size()) {
            Point target = path.get(currentWaypoint);
            double dx = target.getX() - x;
            double dy = target.getY() - y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < speed) {
                x = target.getX();
                y = target.getY();
                currentWaypoint++;
            } else {
                x += (dx / distance) * speed;
                y += (dy / distance) * speed;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int amount) {
        health -= amount;
    }
}
