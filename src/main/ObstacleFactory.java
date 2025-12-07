package main;
public class ObstacleFactory {

    private GamePanel gp;

    public ObstacleFactory(GamePanel gp) {
        this.gp = gp;
    }
    public Obstacle createObstacle(int tileType, int x, int y) {
        switch (tileType) {
            case 2: // spąstai
                return new TrapObstacle(x, y);
            case 3: // raktas
                return new KeyObstacle(x, y, gp);
            case 4: // durys
                return new DoorObstacle(x, y, gp);
            case 6: // išėjimas
                return new ExitObstacle(x, y);
            default:
                return null; // jei nezinomas tipas
        }
    }
}