//This calls represents a boundary for cells, this class can check if a position is within the boundary or not
public class Boundary {

    //These four integers represent the four corners of the boundary
    private final int maxX;
    private final int minX;
    private final int maxY;
    private final int minY;

    //tolerance of the boundary, added to the values to create a buffer that goes off screen to avoid a visible boundary
    private final int tolerance;

    //constructor
    public Boundary (int width, int height, double tolerance) {

        //calculate tolerance value
        this.tolerance = (int) (tolerance * (double) width);

        //apply tolerance to borders
        maxX = width + this.tolerance;
        maxY = height + this.tolerance;
        minX = -this.tolerance;
        minY = -this.tolerance;
    }

    //this constructor is useful to create a boundary from another boundary
    public Boundary (int minX, int maxX, int minY, int maxY, double tolerance) {

        this.tolerance = (int) (tolerance * (double) maxX - (double) minX);

        this.maxX = maxX + this.tolerance;
        this.maxY = maxY + this.tolerance;
        this.minX = minX - this.tolerance;
        this.minY = minY - this.tolerance;
    }

    //checks if a position is outside the boundary
    public boolean outsideBoundary(int x, int y) {
        //if inside boundary return false, if outside return true
        return (x > maxX) || (x < minX) || (y > maxY) || (y < minY);
    }

    //getter methods for boundary corners
    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMinX () {
        return minX;
    }

    public int getMinY () {
        return minY;
    }

}
