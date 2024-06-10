package org.example.game;

import javax.swing.*;

public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Game(){
        JFrame frame = new JFrame("PAGs Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Game();
    }
}
