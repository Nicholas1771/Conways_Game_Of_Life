import java.util.ArrayList;

//This class represents the state of the cells, and whether the cells are paused or not
public class GameState {

    //list of active cells (not necessarily all alive), any dead cell neighbouring a live cell, is active
    private ArrayList<Cell> activeCells;

    //is the gamestate paused
    private boolean paused;

    //new gamestate, pause the gamestate and create empty list of active cells
    public GameState() {
        activeCells = new ArrayList<>();
        paused = true;
    }

    //getters and setters for active cells
    public ArrayList<Cell> getActiveCells() {
        return activeCells;
    }

    public void setActiveCells (ArrayList<Cell> activeCells) {
        this.activeCells = activeCells;
    }

    //unpause the gamestate
    public void start() {
        paused = false;
    }

    //pause the gamestate
    public void pause() {
        paused = true;
    }

    //returns paused value
    public boolean isPaused () {
        return paused;
    }
}
