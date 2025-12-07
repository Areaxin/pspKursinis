package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkTile(Entity entity, String direction) {
        // Apskaičiuojame būsimą poziciją PRIEŠ judant
        int entityLeftX = entity.x;
        int entityRightX = entity.x + gp.tileSize - 1;
        int entityTopY = entity.y;
        int entityBottomY = entity.y + gp.tileSize - 1;

        int offset = 8; // tarpas nuo krašto, kad tikrintų ne visą tile

        int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;

        switch(direction) {
            case "up":
                entityTopY = entityTopY - entity.speed;
                entityTopRow = entityTopY / gp.tileSize;
                entityLeftCol = (entityLeftX + offset) / gp.tileSize;
                entityRightCol = (entityRightX - offset) / gp.tileSize;

                if (isTileBlocking(entityLeftCol, entityTopRow) ||
                        isTileBlocking(entityRightCol, entityTopRow)) {
                    return true;
                }
                break;

            case "down":
                entityBottomY = entityBottomY + entity.speed;
                entityBottomRow = entityBottomY / gp.tileSize;
                entityLeftCol = (entityLeftX + offset) / gp.tileSize;
                entityRightCol = (entityRightX - offset) / gp.tileSize;

                if (isTileBlocking(entityLeftCol, entityBottomRow) ||
                        isTileBlocking(entityRightCol, entityBottomRow)) {
                    return true;
                }
                break;

            case "left":
                entityLeftX = entityLeftX - entity.speed;
                entityLeftCol = entityLeftX / gp.tileSize;
                entityTopRow = (entityTopY + offset) / gp.tileSize;
                entityBottomRow = (entityBottomY - offset) / gp.tileSize;

                if (isTileBlocking(entityLeftCol, entityTopRow) ||
                        isTileBlocking(entityLeftCol, entityBottomRow)) {
                    return true;
                }
                break;

            case "right":
                entityRightX = entityRightX + entity.speed;
                entityRightCol = entityRightX / gp.tileSize;
                entityTopRow = (entityTopY + offset) / gp.tileSize;
                entityBottomRow = (entityBottomY - offset) / gp.tileSize;

                if (isTileBlocking(entityRightCol, entityTopRow) ||
                        isTileBlocking(entityRightCol, entityBottomRow)) {
                    return true;
                }
                break;
        }
        return false;
    }


    private boolean isTileBlocking(int col, int row) {
        // Tikrina ar neišėjome už žemėlapio ribų
        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return true; // už ribų = blokuoja
        }

        // Tikrina ar tile yra siena (tile 1)
        int tileNum = gp.tm.mapTileNum[col][row];
        if (gp.tm.tile[tileNum].collision) {
            return true;
        }


        if (gp.obstacleManager.isBlocking(col, row)) {
            return true;
        }

        return false;
    }

}