package main;

public class RestartCommand implements Command {
    private GamePanel gp;

    public RestartCommand(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void execute() {
        gp.restartGame();
    }
}