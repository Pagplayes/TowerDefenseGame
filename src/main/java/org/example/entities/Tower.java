package org.example.entities;

import java.awt.*;

public class Tower  extends GameObject {
    public Tower(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
