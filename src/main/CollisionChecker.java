package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entityLeftX + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entityTopY + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {

            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
                if (gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true)
                    entity.collisionOn = true;
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true)
                    entity.collisionOn = true;
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                if (gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true)
                    entity.collisionOn = true;
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true)
                    entity.collisionOn = true;
        }

    }
}
