package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;//16x16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;//48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public  final int screenWidth = tileSize * maxScreenCol;//768 pixeliai
    public final int screenHeight = tileSize * maxScreenRow;//576 pixeliai
    int FPS = 60;

    public TileManager tm = new TileManager(this);
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this,keyHandler);
    FogOfWar fogOfWar = new FogOfWar(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){
            long currentTime = System.nanoTime();

            Update();

            repaint();//tik tokiu budu issikvieciu paintComponent metoda


            try {
                double remainingTime =nextDrawTime-System.nanoTime();
                remainingTime = remainingTime/1000000;//paverciu i mili sekundes, nes nepriima nano
                if(remainingTime <0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void Update(){
        if(keyHandler.restartPressed) {
            restartGame();
        }
        player.update();
    }
    public void restartGame() {
        player.setDefaultValues();
        tm.loadMap("/maps/map0.txt");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tm.draw(g2);
        player.draw(g2);
        fogOfWar.draw(g2, player.x, player.y);
        g2.dispose();
    }
}
