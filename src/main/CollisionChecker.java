package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkTile(Entity entity, String direction) {
        int entityLeftX = entity.x;
        int entityRightX = entity.x + gp.tileSize - 1;
        int entityTopY = entity.y;
        int entityBottomY = entity.y + gp.tileSize - 1;

        int offset = 8;

        int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;
        int tileNum1, tileNum2;

        switch(direction) {
            case "up":
                //prediction kur bus po judejimo
                entityTopY = entityTopY - entity.speed;
                entityTopRow = entityTopY / gp.tileSize;
                entityLeftCol = (entityLeftX + offset) / gp.tileSize;
                entityRightCol = (entityRightX - offset) / gp.tileSize;

                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityTopRow];

                if(gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    return true;
                }
                break;

            case "down":
                entityBottomY = entityBottomY + entity.speed;
                entityBottomRow = entityBottomY / gp.tileSize;
                entityLeftCol = (entityLeftX + offset) / gp.tileSize;
                entityRightCol = (entityRightX - offset) / gp.tileSize;

                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];

                if(gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    return true;
                }
                break;

            case "left":
                entityLeftX = entityLeftX - entity.speed;
                entityLeftCol = entityLeftX / gp.tileSize;
                entityTopRow = (entityTopY + offset) / gp.tileSize;
                entityBottomRow = (entityBottomY - offset) / gp.tileSize;

                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];

                if(gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    return true;
                }
                break;

            case "right":
                entityRightX = entityRightX + entity.speed;
                entityRightCol = entityRightX / gp.tileSize;
                entityTopRow = (entityTopY + offset) / gp.tileSize;
                entityBottomRow = (entityBottomY - offset) / gp.tileSize;

                tileNum1 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];

                if(gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
                    return true;
                }
                break;
        }
        return false;
    }
}