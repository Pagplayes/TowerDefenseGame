package org.example.entities;

import java.awt.*;

public class Projectile extends GameObject {
    private int speed;
    private int damage;
    private DamageType damageType;
    private double directionX, directionY;


    public Projectile(int x, int y, int width, int height, int speed, int damage, DamageType damageType, double directionX, double directionY) {
        super(x, y, width, height);
        this.speed = speed;
        this.damage = damage;
        this.damageType = damageType;
        this.directionX = directionX;
        this.directionY = directionY;
    }

    @Override
    public void update() {
        x += speed * directionX;
        y += speed * directionY;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, width, height);
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public DamageType getDamageType() {
        return damageType;
    }

}
