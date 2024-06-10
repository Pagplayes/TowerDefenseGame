package org.example.game;

import org.example.entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GamePanel  extends JPanel {
    private List<GameObject> towers;
    private List<GameObject> enemies;
    private List<Projectile> projectiles;
    private Timer timer;

    public GamePanel(){
        setFocusable(true);
        requestFocus();
        towers = new ArrayList<>();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();

        towers.add(new Tower(600, 100, 40, 40, 400, 10, DamageType.PHYSICAL, 5, projectiles));
        enemies.add(new Enemy(0, 200, 40, 40));

        timer = new Timer(16,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        for (GameObject tower : towers) {
            tower.draw(g);
        }

        for (GameObject enemy : enemies) {
            enemy.draw(g);
        }

        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
    }

    public void update() {
        for (GameObject tower : towers) {
            tower.update();
        }

        Iterator<GameObject> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            GameObject enemy = iterator.next();
            enemy.update();

            if (enemy.getX() > Game.WIDTH) {
                iterator.remove();
            }
        }

        for (GameObject tower : towers) {
            if (tower instanceof Tower) {
                for (GameObject enemy : enemies) {
                    if (enemy instanceof Enemy) {
                        ((Tower) tower).shoot((Enemy) enemy);
                    }
                }
            }
        }

        Iterator<Projectile> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();
            projectile.update();

            if (projectile.getX() > Game.WIDTH || projectile.getY() > Game.HEIGHT || projectile.getX() < 0 || projectile.getY() < 0) {
                projectileIterator.remove();
            }
        }

        repaint();
    }
}
