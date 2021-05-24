import java.util.ArrayList;

public class GameState {

    ArrayList<Cell> activeCells;

    public GameState() {
        init();
    }

    public void init() {
        activeCells = new ArrayList<>();

        activeCells.add(new Cell(10, 10));
        activeCells.add(new Cell(11, 10));
        activeCells.add(new Cell(12, 10));
    }

    public void tick() {

    }

    public void updateActiveCells () {
        for (int i = 0; i < activeCells.size(); i++) {
            if (activeCells.get(i).isAlive()) {

            } else {
                if (!hasAliveNeighbour(activeCells.get(i))) {
                    activeCells.remove(i);
                }
            }
        }
    }

    public boolean hasAliveNeighbour (Cell cell) {
        for (int i = 0; i < activeCells.size(); i++) {
            Cell activeCell = activeCells.get(i);
            if (activeCell.isAlive()) {
                int activeCellX = activeCell.getX();
                int activeCellY = activeCell.getY();
                int cellX = cell.getX();
                int cellY = cell.getY();

                if (cellX == activeCellX && cellY == activeCellY) {
                    continue;
                }

                if (Math.abs(cellX - activeCellX) <= 1 && Math.abs(cellY - activeCellY) <= 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
