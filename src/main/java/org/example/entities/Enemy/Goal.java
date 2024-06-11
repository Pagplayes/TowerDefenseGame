package org.example.entities.Enemy;

import org.example.entities.utils.GameObject;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;


public class Goal  extends GameObject {
    public Goal(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        // The goal doesn't need to update
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
