public class Cell {

    private boolean alive;
    private int x;
    private int y;

    public Cell (int x, int y) {
        this.alive = false;
        this.x = x;
        this.y = y;
    }

    public Cell (int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public void kill() {
        this.alive = false;
    }

    public void live() {
        this.alive = true;
    }

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
