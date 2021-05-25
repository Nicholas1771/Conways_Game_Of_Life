import java.util.ArrayList;

public class GameState {

    private ArrayList<Cell> activeCells;

    private boolean paused;

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public GameState() {
        init();
    }

    public void init() {
        activeCells = new ArrayList<>();
        minX = minY = maxX = maxY = 0;
        updateMaxMin();
        paused = true;
    }

    public void tick() {
        if (!paused) {
            updateActiveCells();
            tickActiveCells();
        }
    }

    private void updateMaxMin() {
        for (Cell cell : activeCells) {
            if (cell.getX() > maxX) {
                maxX = cell.getX();
            } else if (cell.getX() < minX) {
                minX = cell.getX();
            }
            if (cell.getY() > maxY) {
                maxY = cell.getY();
            } else if (cell.getY() < minY) {
                minY = cell.getY();
            }
        }
    }

    private void tickActiveCells() {
        //Create temp copy of the arraylist, changes will be made to the copy
        ArrayList<Cell> copy = new ArrayList<>();
        for (Cell cell : activeCells) {
            copy.add(new Cell(cell.getX(), cell.getY(), cell.isAlive()));
        }
        for (int i = 0; i < activeCells.size(); i++) {
            Cell newCell = copy.get(i);
            Cell oldCell = activeCells.get(i);
            int numNeighbours = numAliveNeighbours(oldCell);
            if (oldCell.isAlive()) {
                if (numNeighbours < 2 || numNeighbours > 3) {
                    newCell.kill();
                }
            } else {
                if (numNeighbours == 3) {
                    newCell.live();
                }
            }
        }
        //replace old arraylist with the copy made earlier, along with all the changes
        activeCells = new ArrayList<>();
        for (Cell cell : copy) {
            activeCells.add(new Cell(cell.getX(), cell.getY(), cell.isAlive()));
        }
    }

    private int numAliveNeighbours (Cell cell) {
        int count = 0;
        int x = cell.getX();
        int y = cell.getY();
        for (Cell activeCell : activeCells) {
            if (activeCell.isAlive()) {
                int activeCellX = activeCell.getX();
                int activeCellY = activeCell.getY();

                if (x == activeCellX && y == activeCellY) {
                    continue;
                }

                if (Math.abs(x - activeCellX) <= 1 && Math.abs(y - activeCellY) <= 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void updateActiveCells () {
        for (int i = 0; i < activeCells.size(); i++) {
            Cell cell = activeCells.get(i);
            int x = cell.getX();
            int y = cell.getY();
            if (cell.isAlive()) {
                //top left
                if (!isMatch(activeCells, x-1, y+1)) {
                    activeCells.add(new Cell(x-1, y+1));
                }
                //top middle
                if (!isMatch(activeCells, x, y+1)) {
                    activeCells.add(new Cell(x, y+1));
                }
                //top right
                if (!isMatch(activeCells, x+1, y+1)) {
                    activeCells.add(new Cell(x+1, y+1));
                }
                //middle left
                if (!isMatch(activeCells, x-1, y)) {
                    activeCells.add(new Cell(x-1, y));
                }
                //middle right
                if (!isMatch(activeCells, x+1, y)) {
                    activeCells.add(new Cell(x+1, y));
                }
                //bottom left
                if (!isMatch(activeCells, x-1, y-1)) {
                    activeCells.add(new Cell(x-1, y-1));
                }
                //bottom middle
                if (!isMatch(activeCells, x, y-1)) {
                    activeCells.add(new Cell(x, y-1));
                }
                //bottom right
                if (!isMatch(activeCells, x+1, y-1)) {
                    activeCells.add(new Cell(x+1, y-1));
                }
            } else {
                if (numAliveNeighbours(activeCells.get(i)) == 0) {
                    activeCells.remove(i);
                }
            }
        }
    }

    public boolean isMatch (ArrayList<Cell> cells, int x, int y) {
        for (Cell cell : cells) {
            if (cell.getX() == x && cell.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private int getCellIndex (int x, int y) {
        for (int i = 0; i < activeCells.size(); i++) {
            if (activeCells.get(i).getX() == x && activeCells.get(i).getY() == y) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Cell> getActiveCells() {
        return activeCells;
    }

    public void toggleCell (int x, int y) {
        if (!isMatch(activeCells, x, y)) {
            activeCells.add(new Cell(x, y, true));
        } else {
            activeCells.remove(getCellIndex(x, y));
        }
    }

    public void start() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }
}
