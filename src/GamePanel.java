import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Grid grid;

    public GamePanel (Grid grid) {
        panelInit();
        this.grid = grid;
    }

    private void panelInit() {
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i < grid.getNumCellsX(); i++) {
            g.drawLine(i * grid.getCellSize(), 0, i * grid.getCellSize(), getHeight());
        }
        for (int i = 0; i < grid.getNumCellsX(); i++) {
            g.drawLine(0, i * grid.getCellSize(), getWidth(),i * grid.getCellSize());
        }
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
