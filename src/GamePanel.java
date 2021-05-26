import javax.swing.*;
import java.awt.*;

//This class is the panel in the center of the jframe, it draws the cells
public class GamePanel extends JPanel {

    //Determines how small the cells will be rendered (how many fit across the screen)
    private final int CELLS_ACROSS = 100;

    //gamestate of the cells
    private GameState gameState;

    //cell width and height (square)
    private int cellSize;

    //constructor
    public GamePanel () {
        gameState = new GameState();
    }

    //draw the cells
    public void paint(Graphics g) {
        super.paint(g);
        drawCells(g);
    }

    //draw the cells in the correct position
    private void drawCells(Graphics g) {
        //calculate the cell size
        cellSize = getWidth() / CELLS_ACROSS;

        //loop through the gamestate's active cells
        for (int i = 0; i < gameState.getActiveCells().size(); i++) {
            //if the cell is alive, draw the cell in orange in the correct position
            if (gameState.getActiveCells().get(i).isAlive()) {
                //draw cell in orange
                g.setColor(Color.ORANGE);
                //multiply cell x and y position by cell size to draw at correct positions
                g.fillRect(gameState.getActiveCells().get(i).getX() * cellSize, gameState.getActiveCells().get(i).getY() * cellSize, cellSize - 1, cellSize - 1);
                g.setColor(Color.BLACK); //back to black (default)
            }
        }
    }

    //getter and setter for cellSize and gameState
    public int getCellSize () {
        return cellSize;
    }

    public void setGameState (GameState gameState) {
        this.gameState = gameState;
    }
}
