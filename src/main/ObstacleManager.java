package main;

import entity.Player;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {

    private GamePanel gp;
    private ObstacleFactory factory;

    private List<Obstacle> obstacles;

    public ObstacleManager(GamePanel gp) {
        this.gp = gp;
        this.obstacles = new ArrayList<>();
        this.factory = new ObstacleFactory(gp);
    }

    // Prideda kliuti i sarasą
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    // isvalo visas kliutis
    public void clearObstacles() {
        obstacles.clear();
    }

    public void loadObstaclesFromMap(int[][] mapTileNum, int maxCol, int maxRow) {
        clearObstacles();

        for (int col = 0; col < maxCol; col++) {
            for (int row = 0; row < maxRow; row++) {
                int tileNum = mapTileNum[col][row];
                Obstacle obstacle = factory.createObstacle(tileNum, col, row);

                if (obstacle != null) {
                    addObstacle(obstacle);
                }
            }
        }
    }

    // polimirfizmas
    // Tikrina saveika su zaideju
    public void checkInteractions(Player player) {

        int playerCenterX = player.x + gp.tileSize / 2;
        int playerCenterY = player.y + gp.tileSize / 2;
        int playerCol = playerCenterX / gp.tileSize;
        int playerRow = playerCenterY / gp.tileSize;

        // Tikrina tile kur žaidėjas YRA
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == playerCol && obstacle.getY() == playerRow) {
                obstacle.interact(player);
            }
        }

        //Tikrina tile PRIEŠAIS žaidėją (durims atrakinti)
        checkAdjacentTile(player, playerCol, playerRow);
    }

    // NAUJAS metodas - tikrina tile priešais žaidėją
    private void checkAdjacentTile(Player player, int currentCol, int currentRow) {
        int adjacentCol = currentCol;
        int adjacentRow = currentRow;

        // Apskaičiuojame tile priklausomai nuo krypties
        switch(player.direction) {
            case "up":
                adjacentRow = currentRow - 1;
                break;
            case "down":
                adjacentRow = currentRow + 1;
                break;
            case "left":
                adjacentCol = currentCol - 1;
                break;
            case "right":
                adjacentCol = currentCol + 1;
                break;
        }


        if (adjacentCol < 0 || adjacentCol >= gp.maxScreenCol ||
                adjacentRow < 0 || adjacentRow >= gp.maxScreenRow) {
            return;
        }

        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == adjacentCol && obstacle.getY() == adjacentRow) {
                if (obstacle.isBlocking()) {
                    obstacle.interact(player);
                }
            }
        }
    }

    public boolean isBlocking(int col, int row) {
        // polimofrizmas
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == col && obstacle.getY() == row) {
                if (obstacle.isBlocking()) {
                    return true;
                }
            }
        }
        return false;
    }

    //p
    public void draw(Graphics2D g2) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g2, gp.tileSize);
        }
    }
    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}