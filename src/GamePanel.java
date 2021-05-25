import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private GameState gameState;

    private int cellSize;

    private final int cellsAcross = 100;

    public GamePanel () {
        gameState = new GameState();
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawCells(g);
    }

    public void setGameState (GameState gameState) {
        this.gameState = gameState;
    }

    private void drawCells(Graphics g) {
        cellSize = getWidth() / cellsAcross;
        for (Cell cell : gameState.getActiveCells()) {
            if (cell.isAlive()) {
                g.setColor(Color.ORANGE);
                g.fillRect(cell.getX() * cellSize, cell.getY() * cellSize, cellSize-1, cellSize-1);
                g.setColor(Color.BLACK);
            }
        }
    }

    public int getCellSize () {
        return cellSize;
    }
}
