package main;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Urvinis Robotozas");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();//padaro kad langas butu pagal mano nustatyma, nes dabar be jo 0x0 gaunasi

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();


    }
}