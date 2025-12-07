package main;

import entity.Player;
import java.awt.Graphics2D;

public abstract class Obstacle {

    protected int x;
    protected int y;

    protected boolean active;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
        this.active = true;
    }


    public abstract void interact(Player player);
    public abstract boolean isBlocking();

    public abstract void draw(Graphics2D g2, int tileSize);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}