/**
 * A column and row on the board. The origin {@code (0, 0)} is the top-left
 * corner: {@code x} increases to the right and {@code y} increases downward.
 */
public class Coordinate
{
    //~ Fields ................................................................
    private int x;
    private int y;

    //~ Constructors ..........................................................

    /**
     * Creates a coordinate at the given column and row.
     *
     * @param x the column
     * @param y the row
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //~Public  Methods ........................................................

    /**
     * Returns the column.
     *
     * @return the x value
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the row.
     *
     * @return the y value
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the column.
     *
     * @param x the new x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the row.
     *
     * @param y the new y value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns this coordinate as {@code Coordinate [x=column, y=row]}.
     *
     * @return a string containing the column and row
     */
    @Override
    public String toString() {
        return "Coordinate [x=" + x + ", y=" + y + "]";
    }

    /**
     * Returns whether this coordinate names the same column and row as
     * another coordinate.
     *
     * @param obj the object to compare
     * @return true when {@code obj} is a coordinate with the same x and y
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
}
