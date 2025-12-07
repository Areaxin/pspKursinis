package main;

import entity.Player;
import java.awt.Graphics2D;

public class DoorObstacle extends Obstacle {

    private boolean locked;
    private GamePanel gp;

    public DoorObstacle(int x, int y, GamePanel gp) {
        super(x, y);
        this.locked = true;
        this.gp = gp;
    }

    @Override
    public void interact(Player player) {
        if (locked && player.getKeys() > 0) {
            player.useKey();
            locked = false;
            gp.tm.mapTileNum[x][y] = 5; // pakeicia tile 4 i 5
            gp.tm.tile[5].collision = false;
            System.out.println("Liko raktų: " + player.getKeys());
        }
    }

    @Override
    public boolean isBlocking() {
        return locked;
    }

    @Override
    public void draw(Graphics2D g2, int tileSize) {

    }

    public boolean isLocked() {
        return locked;
    }
}