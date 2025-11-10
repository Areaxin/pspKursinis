package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
   public Tile[] tile;
    public int mapTileNum[][];


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/map0.txt");
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/tiles/ground.png"));
            tile[0].collision = false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("/tiles/poison.png"));
            tile[2].collision = false;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResource("/tiles/key.png"));
            tile[3].collision = false;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResource("/tiles/door_locked.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResource("/tiles/door_unlocked.png"));
            tile[5].collision = false;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResource("/tiles/exit.png"));
            tile[6].collision = false;
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<gp.maxScreenCol && row<gp.maxScreenRow){
                String line = br.readLine();
                while(col<gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;//cia idedam paimtus skaicius
                    col++;//pildom kol visi sk bus sudeti
                }
                if(col == gp.maxScreenCol){//kai pasiekia 16 vienos eiles
                    col=0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int col=0, row=0, x=0, y=0;
        while(col<gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x += gp.tileSize;
            if(col==gp.maxScreenCol){
                col=0;
                x=0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
