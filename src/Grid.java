public class Grid {

    private int numCellsX;
    private int numCellsY;
    private int cellSize;

    public Grid (int numCellsX, int numCellsY) {
        this.numCellsX = numCellsX;
        this.numCellsY = numCellsY;
        this.cellSize = 10;
    }

    public int getNumCellsX() {
        return this.numCellsX;
    }

    public int getNumCellsY() {
        return this.numCellsY;
    }

    public int getCellSize() {
        return cellSize;
    }
}
