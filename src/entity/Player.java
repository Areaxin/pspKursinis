package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;
    public int keys = 0;
    public boolean alive = true;
    public boolean won = false;


    public Player(GamePanel gp, KeyHandler keyHandler) {
            this.gp = gp;
            this.keyHandler = keyHandler;
            setDefaultValues();
            getPlayerImage();
        }
        public void setDefaultValues() {
            x=100;
            y=100;
            speed=4;
            direction = "down";
            keys = 0;
            alive = true;
            won = false;
        }
        public void getPlayerImage(){
            try {
                up1 = ImageIO.read(getClass().getResourceAsStream("/player/robot1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/player/robot2.png"));

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            }

    public void update() {
        if(!alive || won) {
            return;
        }


        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

            if(keyHandler.upPressed){
                direction = "up";
            }
            else if(keyHandler.downPressed){
                direction = "down";
            }
            else if(keyHandler.leftPressed){
                direction = "left";
            }
            else if(keyHandler.rightPressed){
                direction = "right";
            }
            checkDoorInteraction();

            //tikriname koliziją pries judant
            collisionOn = gp.collisionChecker.checkTile(this, direction);

            if(!collisionOn) {
                switch(direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }
            }
        }

        //tikriname interakciją kiekviena frame
        checkTileInteraction();
    }


    public void checkDoorInteraction() {
        int playerCenterX = x + gp.tileSize / 2;
        int playerCenterY = y + gp.tileSize / 2;

        int col = playerCenterX / gp.tileSize;
        int row = playerCenterY / gp.tileSize;

        int nextCol = col;
        int nextRow = row;

        switch(direction) {
            case "up":
                nextRow = (playerCenterY - gp.tileSize/2 - speed) / gp.tileSize;
                break;
            case "down":
                nextRow = (playerCenterY + gp.tileSize/2 + speed) / gp.tileSize;
                break;
            case "left":
                nextCol = (playerCenterX - gp.tileSize/2 - speed) / gp.tileSize;
                break;
            case "right":
                nextCol = (playerCenterX + gp.tileSize/2 + speed) / gp.tileSize;
                break;
        }

        // ar nesame uz ribu
        if(nextCol < 0 || nextCol >= gp.maxScreenCol || nextRow < 0 || nextRow >= gp.maxScreenRow) {
            return;
        }

        int nextTileNum = gp.tm.mapTileNum[nextCol][nextRow];

        //jei uzrakintos durys ir turim rakta
        if(nextTileNum == 4 && keys > 0) {
            keys--;
            gp.tm.mapTileNum[nextCol][nextRow] = 5;
            gp.tm.tile[5].collision = false;
        }
    }

    public void checkTileInteraction() {

        int playerCenterX = x + gp.tileSize / 2;
        int playerCenterY = y + gp.tileSize / 2;

        int col = playerCenterX / gp.tileSize;
        int row = playerCenterY / gp.tileSize;

        if(col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return;
        }

        int tileNum = gp.tm.mapTileNum[col][row];

        if(tileNum == 2 && alive) {
            alive = false;

        }
        if(tileNum == 3) {
            keys++;
            gp.tm.mapTileNum[col][row] = 0;
        }

        if(tileNum == 6 && !won) {
            won = true;
            System.out.println("you escaped the cave");
        }
    }
        public void draw(Graphics2D g2) {
            //g2.setColor(Color.WHITE);
            //g2.fillRect(x,y,gp.tileSize,gp.tileSize);
            BufferedImage image = null;
            switch (direction) {
                case "up":
                    image = up1;
                    break;
                case "down":
                    image = up2;
                    break;
                case "right":
                    image = up2;
                    break;
                case "left":
                    image = up1;
                    break;

            }
            g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
            /// ------------------------------------------------------------------------------------------
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Raktai: " + keys, 10, 25);

            if(!alive) {
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

                g2.setColor(Color.RED);
                g2.setFont(new Font("Arial", Font.BOLD, 40));
                String msg = "dead";
                int msgWidth = g2.getFontMetrics().stringWidth(msg);
                g2.drawString(msg, gp.screenWidth/2 - msgWidth/2, gp.screenHeight/2 - 20);

                g2.setFont(new Font("Arial", Font.PLAIN, 25));
                String restart = "R to restart";
                int restartWidth = g2.getFontMetrics().stringWidth(restart);
                g2.drawString(restart, gp.screenWidth/2 - restartWidth/2, gp.screenHeight/2 + 20);
            }

            if(won) {
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

                g2.setColor(Color.GREEN);
                g2.setFont(new Font("Arial", Font.BOLD, 40));
                String msg = "you win";
                int msgWidth = g2.getFontMetrics().stringWidth(msg);
                g2.drawString(msg, gp.screenWidth/2 - msgWidth/2, gp.screenHeight/2 - 20);

                g2.setFont(new Font("Arial", Font.PLAIN, 25));
                String restart = "R to restart";
                int restartWidth = g2.getFontMetrics().stringWidth(restart);
                g2.drawString(restart, gp.screenWidth/2 - restartWidth/2, gp.screenHeight/2 + 20);
            }

        }
}
