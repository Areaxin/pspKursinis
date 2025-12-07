package main;

import entity.Player;
import java.awt.Graphics2D;

public class KeyObstacle extends Obstacle {
    private GamePanel gp;


    public KeyObstacle(int x, int y, GamePanel gp) {
        super(x, y);
        this.gp = gp;
    }

    @Override

    public void interact(Player player) {
        if (active) {
            player.addKey();
            active = false;
            gp.tm.mapTileNum[x][y] = 0;
            System.out.println(player.getKeys());
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