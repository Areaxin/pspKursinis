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
            if(keyHandler.upPressed == true){
                direction = "up";
                y -= speed;
            }
            else if(keyHandler.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if(keyHandler.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if(keyHandler.rightPressed == true){
                direction = "right";
                x += speed;
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
        }
}
