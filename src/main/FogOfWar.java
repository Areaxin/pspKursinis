package main;

import java.awt.*;

public class FogOfWar {
    GamePanel gp;
    private int visionRange = 2;

    public FogOfWar(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2, int playerX, int playerY) {
        int visibleWidth = (visionRange * 2 + 1) * gp.tileSize;
        int visibleHeight = (visionRange * 2 + 1) * gp.tileSize;

        int visibleLeft = playerX - visionRange * gp.tileSize;
        int visibleRight = playerX + gp.tileSize + visionRange * gp.tileSize;
        int visibleTop = playerY - visionRange * gp.tileSize;
        int visibleBottom = playerY + gp.tileSize + visionRange * gp.tileSize;

        g2.setColor(Color.BLACK);

        if(visibleTop > 0) {
            g2.fillRect(0, 0, gp.screenWidth, visibleTop);
        }

        if(visibleBottom < gp.screenHeight) {
            g2.fillRect(0, visibleBottom, gp.screenWidth, gp.screenHeight - visibleBottom);
        }

        if(visibleLeft > 0) {
            g2.fillRect(0, visibleTop, visibleLeft, visibleHeight);
        }

        if(visibleRight < gp.screenWidth) {
            g2.fillRect(visibleRight, visibleTop, gp.screenWidth - visibleRight, visibleHeight);
        }
    }
}