package org.example.entities;

import java.awt.*;

public class Enemy extends GameObject {

    private int speed;


    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = 1;
    }

    @Override
    public void update() {
        x += 1;
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
}
