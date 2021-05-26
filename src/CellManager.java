import java.util.ArrayList;

//This class is used to manage the cells and kill cells outside of the boundary
public class CellManager {

    //a cell passing the hard boundary will trigger death of all cells within the sweep boundary
    private Boundary hardBoundary;
    //sweep boundary represents the boundary of cells to be killed when the hard boundary is passed
    private Boundary sweepBoundary;

    //sweep tolerance represents how much bigger the hard boundary is (0.1 = 10% bigger)
    private final double HARD_TOLERANCE = 0.1;

    //constructor
    public CellManager (Boundary boundary) {
        sweepBoundary = boundary;
        //create the hard boundary similar to the sweep boundary, but 10% bigger
        hardBoundary = new Boundary (sweepBoundary.getMinX(), sweepBoundary.getMaxX(), sweepBoundary.getMinY(), sweepBoundary.getMaxY(), HARD_TOLERANCE);
    }

    //If a cell has passed the hard boundary, kill all cells outside the sweep boundary
    public void verifyCellPositions (ArrayList<Cell> cells) {
        //loop through all cells
        for (Cell cell : cells) {
            //if the cell is not within the hard boundary, kill all cells passed the sweep boundary
            if (hardBoundary.outsideBoundary(cell.getX(), cell.getY())) {
                sweepOutsideCells(cells);
            }
        }
    }

    //This method kills all cells passed the sweep boundary
    private void sweepOutsideCells (ArrayList<Cell> cells) {
        //loop through all cells
        for (Cell cell : cells) {
            if (sweepBoundary.outsideBoundary(cell.getX(), cell.getY())) {
                //kill cells outside sweep boundary
                cell.kill();
            }
        }
    }
}
