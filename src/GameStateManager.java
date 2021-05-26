import java.util.ArrayList;

//This class is responsible for managing the gamestate
public class GameStateManager {

    //Gamestate holds the information of the current game state
    private GameState gameState;
    //Cell manager used to monitor cells and kill them if they go out of boundary
    private CellManager cellManager;

    //Boundary lengths (# of cells)
    private final int BOUNDARY_HORIZONTAL = 100;
    private final int BOUNDARY_VERTICAL = 50;
    //Tolerance for the boundary (0.2 = +20%) to allow cells to move 20% offscreen before they are killed
    private final double BOUNDARY_TOLERANCE = 0.2;

    //constructor
    public GameStateManager () {
        //Call method to create a new gamestate
        newGameState();
        //initialize the cellManager and give it the boundary information
        cellManager = new CellManager(new Boundary(BOUNDARY_HORIZONTAL, BOUNDARY_VERTICAL, BOUNDARY_TOLERANCE));
    }

    //this method performs a "tick" on the game, moving from one state to the next
    public void tick() {
        //perform the "tick" if the gamestate is not paused
        if (!gameState.isPaused()) {
            //Use the cell manager to verify all alive cell positions, killing those out of bounds
            verifyCellPositions();
            //Update the list of active cells, active does not necessarily mean "alive"
            updateActiveCells();
            //Tick the active cells moving them to the next state based on the rules
            tickActiveCells();
        }
    }

    //This method uses the cell manager to verify all cells are within the bounds
    private void verifyCellPositions () {
        cellManager.verifyCellPositions(gameState.getActiveCells());
    }

    private void tickActiveCells() {
        //Create temp copy of the arraylist, changes will be made to the copy
        ArrayList<Cell> copy = new ArrayList<>();
        for (Cell cell : gameState.getActiveCells()) {
            copy.add(new Cell(cell.getX(), cell.getY(), cell.isAlive()));
        }
        //Loop through all active cells
        for (int i = 0; i < gameState.getActiveCells().size(); i++) {
            //get the same cell from both the copy and activeCells
            Cell newCell = copy.get(i);
            Cell oldCell = gameState.getActiveCells().get(i);
            //Get the number of alive cell neighbours
            int numNeighbours = numAliveNeighbours(oldCell);
            if (oldCell.isAlive()) {
                //If the cell is alive, and it has less than 2 or more than 3 neighbours, kill it
                if (numNeighbours < 2 || numNeighbours > 3) {
                    newCell.kill();
                }
            } else {
                //If the cell is dead, and it has 3 alive neighbours, make it alive
                if (numNeighbours == 3) {
                    newCell.live();
                }
            }
        }
        //replace old arraylist with the copy made earlier, along with all the changes
        ArrayList<Cell> activeCells = new ArrayList<>();
        for (Cell cell : copy) {
            activeCells.add(new Cell(cell.getX(), cell.getY(), cell.isAlive()));
        }
        //update the gamestate active cells to the changed values
        gameState.setActiveCells(activeCells);
    }

    //Loop through all cells and count the number of alive neighbours of the passed in cell
    private int numAliveNeighbours (Cell cell) {
        //count of alive neighbours
        int count = 0;
        //get x and y positions of the cell (within the cell grid)
        int x = cell.getX();
        int y = cell.getY();
        //loop through all active cells
        for (Cell activeCell : gameState.getActiveCells()) {
            //continue if the cell is alive
            if (activeCell.isAlive()) {
                //if the cell is alive, get its x and y
                int activeCellX = activeCell.getX();
                int activeCellY = activeCell.getY();

                //if the loop cell is the same as the passed in cell, continue, dont count it
                if (x == activeCellX && y == activeCellY) {
                    continue;
                }

                //if the active cell is within 1 cell distance on both x and y axis, add one to count, it is a neighbour
                if (Math.abs(x - activeCellX) <= 1 && Math.abs(y - activeCellY) <= 1) {
                    count++;
                }
            }
        }
        //return number of alive neighbours
        return count;
    }

    //this method is used to recalculate all active cells, removing some, and adding some
    public void updateActiveCells () {
        //loop through all active cells
        for (int i = 0; i < gameState.getActiveCells().size(); i++) {
            //save the cell and its x and y position
            Cell cell = gameState.getActiveCells().get(i);
            int x = cell.getX();
            int y = cell.getY();
            //if the cell is alive, check all of its neighbours, adding new active cells for empty neighbour positions
            if (cell.isAlive()) {
                //top left
                if (!isMatch(gameState.getActiveCells(), x - 1, y + 1)) {
                    gameState.getActiveCells().add(new Cell(x - 1, y + 1));
                }
                //top middle
                if (!isMatch(gameState.getActiveCells(), x, y + 1)) {
                    gameState.getActiveCells().add(new Cell(x, y + 1));
                }
                //top right
                if (!isMatch(gameState.getActiveCells(), x + 1, y + 1)) {
                    gameState.getActiveCells().add(new Cell(x + 1, y + 1));
                }
                //middle left
                if (!isMatch(gameState.getActiveCells(), x - 1, y)) {
                    gameState.getActiveCells().add(new Cell(x - 1, y));
                }
                //middle right
                if (!isMatch(gameState.getActiveCells(), x + 1, y)) {
                    gameState.getActiveCells().add(new Cell(x + 1, y));
                }
                //bottom left
                if (!isMatch(gameState.getActiveCells(), x - 1, y - 1)) {
                    gameState.getActiveCells().add(new Cell(x - 1, y - 1));
                }
                //bottom middle
                if (!isMatch(gameState.getActiveCells(), x, y - 1)) {
                    gameState.getActiveCells().add(new Cell(x, y - 1));
                }
                //bottom right
                if (!isMatch(gameState.getActiveCells(), x + 1, y - 1)) {
                    gameState.getActiveCells().add(new Cell(x + 1, y - 1));
                }
            } else {
                //if the active cell is dead and it has no alive neighbours, remove it from active cell list
                if (numAliveNeighbours(gameState.getActiveCells().get(i)) == 0) {
                    gameState.getActiveCells().remove(i);
                }
            }
        }
    }

    //reset to a new gamestate
    public void newGameState() {
        this.gameState = new GameState();
    }

    //toggle a cell in a specific position from live to dead, or dead to live
    public void toggleCell (int x, int y) {
        if (!isMatch(gameState.getActiveCells(), x, y)) {
            //cell position is not active, add alive cell in this position
            gameState.getActiveCells().add(new Cell(x, y, true));
        } else {
            //cell position is active, remove the cell in that position
            gameState.getActiveCells().remove(getCellIndex(x, y));
        }
    }

    //Check if a specific cell position exists and an arraylist of cells
    public boolean isMatch (ArrayList<Cell> cells, int x, int y) {
        //loop through all cells
        for (Cell cell : cells) {
            //if x and y match, return true
            if (cell.getX() == x && cell.getY() == y) {
                return true;
            }
        }
        //no match was found return false
        return false;
    }

    //get the index of the cell in a specific position
    private int getCellIndex (int x, int y) {
        //loop through all active cells
        for (int i = 0; i < gameState.getActiveCells().size(); i++) {
            //if the cell position matches, return that cell index
            if (gameState.getActiveCells().get(i).getX() == x && gameState.getActiveCells().get(i).getY() == y) {
                return i;
            }
        }
        //no match return -1
        return -1;
    }

    //start the gamestate
    public void start() {
        gameState.start();
    }

    //pause the gamestate
    public void pause () {
        gameState.pause();
    }

    //get the gamestate
    public GameState getGameState() {
        return this.gameState;
    }
}
