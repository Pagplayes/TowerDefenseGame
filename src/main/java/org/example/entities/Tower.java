package org.example.entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tower  extends GameObject {

    private int range;
    private int damage;
    private DamageType damageType;
    private int projectileSpeed;
    private List<Projectile> projectiles;
    private int cooldown;
    private int cooldownTimer;

    public Tower(int x, int y, int width, int height, int range, int damage, DamageType damageType, int projectileSpeed, List<Projectile> projectiles, int cooldown, int cooldownTimer) {
        super(x, y, width, height);
        this.range = range;
        this.damage = damage;
        this.damageType = damageType;
        this.projectileSpeed = projectileSpeed;
        this.projectiles = projectiles;
        this.cooldown = cooldown;
        this.cooldownTimer = cooldownTimer;
    }

    public Tower(int x, int y, int width, int height, int range, int damage, DamageType damageType, int projectileSpeed, List<Projectile> projectiles) {
        this(x,y,width,height,range,damage,damageType,projectileSpeed,projectiles,30,0);
    }

    @Override
    public void update() {
        for (Projectile projectile : projectiles) {
            projectile.update();
        }

        if (cooldownTimer > 0) {
            cooldownTimer--;
        }
    }

    public void shoot(Enemy target) {
        int targetX = target.getX();
        int targetY = target.getY();

        if (isInRange(targetX, targetY) && cooldownTimer == 0) {
            // Berechne die vorausgesagte Position des Gegners
            double dx = targetX - (x + width / 2);
            double dy = targetY - (y + height / 2);
            double distance = Math.sqrt(dx * dx + dy * dy);
            double timeToTarget = distance / projectileSpeed;

            double predictedX = targetX + target.getSpeed() * timeToTarget;
            double predictedY = targetY;

            // Erneut Berechnung der Richtung und Normalisierung
            dx = predictedX - (x + width / 2);
            dy = predictedY - (y + height / 2);
            distance = Math.sqrt(dx * dx + dy * dy);
            double directionX = dx / distance;
            double directionY = dy / distance;

            projectiles.add(new Projectile(x + width / 2, y + height / 2, 10, 10, projectileSpeed, damage, damageType, directionX, directionY));
            cooldownTimer = cooldown;  // reset cooldown
        }
    }

    private boolean isInRange(int targetX, int targetY) {
        double distance = Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
        return distance <= range;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);

        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
    }

}
