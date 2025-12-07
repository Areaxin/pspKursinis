package main;

import entity.Player;
import java.awt.Graphics2D;
import java.awt.Color;

public class TrapObstacle extends Obstacle {
    public TrapObstacle(int x, int y) {
        super(x, y);
    }

    @Override
    public void interact(Player player) {
        if (active) {
            player.setAlive(false);
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