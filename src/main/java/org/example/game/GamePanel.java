package org.example.game;

import org.example.entities.Enemy;
import org.example.entities.GameObject;
import org.example.entities.Tower;

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
    private Timer timer;

    public GamePanel(){
        setFocusable(true);
        requestFocus();
        towers = new ArrayList<>();
        enemies = new ArrayList<>();

        towers.add(new Tower(100,100,40,40));
        enemies.add(new Enemy(0,200,40,40));

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
                iterator.remove(); // Entfernt den Gegner, wenn er das Spielfeld verlässt
            }
        }


        for (GameObject tower : towers) {
            for (GameObject enemy : enemies) {
                if (tower.getBounds().intersects(enemy.getBounds())) {
                    iterator.remove(); // Entfernt den Gegner bei Kollision
                }
            }
        }

        repaint();
    }
}
