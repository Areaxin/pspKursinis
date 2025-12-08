package main;
public class ObstacleFactory {

    private GamePanel gp;

    public ObstacleFactory(GamePanel gp) {
        this.gp = gp;
    }
    public Obstacle createObstacle(int tileType, int x, int y) {
        switch (tileType) {
            case 2:
                return new TrapObstacle(x, y);
            case 3:
                return new KeyObstacle(x, y, gp);
            case 4:
                return new DoorObstacle(x, y, gp);
            case 6:
                return new ExitObstacle(x, y);
            default:
                return null;
        }
    }
}