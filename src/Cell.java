//This class represents a single cell in the Game Of Life
public class Cell {

    //is the cell alive
    private boolean alive;
    //x and y position of cell in the cell grid
    private final int x;
    private final int y;

    //constructor
    public Cell (int x, int y) {
        this.alive = false;
        this.x = x;
        this.y = y;
    }

    //constructor, can create living cell
    public Cell (int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    //kill the cell
    public void kill() {
        this.alive = false;
    }

    //make cell alive
    public void live() {
        this.alive = true;
    }

    //getters for alive, x, and y
    public boolean isAlive() {
        return alive;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }
}
