package org.example.game;

import org.example.entities.Enemy.Enemy;
import org.example.entities.Enemy.Goal;
import org.example.entities.Level.Level;
import org.example.entities.Level.LevelManager;
import org.example.entities.Tower.Projectile;
import org.example.entities.Tower.Tower;
import org.example.entities.utils.DamageType;
import org.example.entities.utils.GameObject;
import org.example.entities.utils.Spawner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GamePanel extends JPanel {

    private List<GameObject> towers;
    private List<GameObject> enemies;
    private List<Projectile> projectiles;
    private Goal goal;
    private Timer timer;
    private Spawner spawner;
    private int playerHealth;
    private JButton replayButton;
    private LevelManager levelManager;

    public GamePanel() {
        setFocusable(true);
        requestFocus();
        towers = new ArrayList<>();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        playerHealth = 10;

        towers.add(new Tower(100, 100, 40, 40, 200, 10, DamageType.PHYSICAL, 5, projectiles));

        levelManager = new LevelManager();
        loadLevel(levelManager.getCurrentLevel());

        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        timer.start();

        replayButton = new JButton("Replay");
        replayButton.setBounds(350, 250, 100, 50);
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        replayButton.setVisible(false);
        add(replayButton);
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

        goal.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Health: " + playerHealth, 10, 20);

        if (playerHealth < 1) {
            g.setColor(Color.RED);
            g.drawString("Game Over", 350, 200);
        }
    }

    private void loadLevel(Level level) {
        goal = level.getGoal();
        spawner = new Spawner(enemies, level.getPath(), 2000, level.getMaxEnemies(), 10, 2); // Example health and speed
        spawner.start();
    }

    public void update() {
        if (playerHealth < 1) {
            timer.stop();
            spawner.stop();
            replayButton.setVisible(true);
            return;
        }

        for (GameObject tower : towers) {
            tower.update();
        }

        Iterator<GameObject> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            GameObject enemy = iterator.next();
            enemy.update();

            if (goal.getBounds().intersects(enemy.getBounds())) {
                iterator.remove();
                playerHealth--;
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

        // Check for collisions between projectiles and enemies
        Iterator<Projectile> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();
            projectile.update();

            boolean hit = false;
            for (GameObject enemy : enemies) {
                if (enemy instanceof Enemy && projectile.getBounds().intersects(enemy.getBounds())) {
                    ((Enemy) enemy).reduceHealth(projectile.getDamage());
                    hit = true;
                    if (((Enemy) enemy).getHealth() < 1) {
                        iterator.remove();
                    }
                    break;
                }
            }

            if (hit || projectile.getX() > Game.WIDTH || projectile.getY() > Game.HEIGHT || projectile.getX() < 0 || projectile.getY() < 0) {
                projectileIterator.remove();
            }
        }

        if (enemies.isEmpty() && !spawner.isRunning()) {
            nextLevel();
        }

        repaint();
    }

    public void nextLevel() {
        levelManager.nextLevel();
        loadLevel(levelManager.getCurrentLevel());
        resetGame();
    }

    private void resetGame() {
        playerHealth = 10;
        enemies.clear();
        projectiles.clear();
        replayButton.setVisible(false);
        spawner.start();
        timer.start();
    }
}
