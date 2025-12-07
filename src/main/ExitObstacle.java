package main;

import entity.Player;
import java.awt.Graphics2D;

public class ExitObstacle extends Obstacle {

    public ExitObstacle(int x, int y) {
        super(x, y);
    }

    @Override
    public void interact(Player player) {
        // Jei ant išėjimo - laimi
        if (active) {
            player.setWon(true); // naudojame setter metodą
            System.out.println("Pasiekei išėjimą!");
        }
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public void draw(Graphics2D g2, int tileSize) {

    }
}