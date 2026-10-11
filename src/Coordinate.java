/** Represents a row and column on the Snake Battle board */
public class Coordinate 
{
    private int row;
    private int col;

    /** Creates a coordinate. Negative indexes are not valid */
    public Coordinate(int row, int col) 
    {
        if (row < 0 || col < 0) 
        {
            throw new IllegalArgumentException("Row and column must be non-negative");
        }
        this.row = row;
        this.col = col;
    }
    /**
     * Gets the row of this coordinate
     * @return the row position
     */
    public int getRow() 
    {
        return row;
    }
    /**
     * Gets the column of this coordinate
     * @return the column position
     */
    public int getCol() 
    {
        return col;
    }
    /**
     * Changes the row of this coordinate
     * @param row the new row position
     * @throws IllegalArgumentException if the row is negative
     */
    public void setRow(int row) 
    {
        if (row < 0) 
        {
            throw new IllegalArgumentException("Row must be non-negative");
        }
        this.row = row;
    }
    /**
     * Changes the column of this coordinate
     * @param col the new column position
     * @throws IllegalArgumentException if the column is negative
     */
    public void setCol(int col) 
    {
        if (col < 0) 
        {
            throw new IllegalArgumentException("Column must be non-negative");
        }
        this.col = col;
    }
    /**
     * Checks whether another object has the same row and column
     * @param other the object to compare with this coordinate
     * @return true if both coordinates have the same row and column, false otherwise
     */
    @Override
    public boolean equals(Object other) 
    {
        if (this == other) 
        {
            return true;
        }
        if (!(other instanceof Coordinate)) 
        {
            return false;
        }

        Coordinate coordinate = (Coordinate) other;
        return row == coordinate.row && col == coordinate.col;
    }
    /**
     * Calculates a hash code based on this coordinate's row and column
     * @return the hash code for this coordinate
     */
    @Override
    public int hashCode() 
    {
        return 31 * row + col;
    }
    /**
     * Converts this coordinate into a readable string
     * @return the coordinate in the format (row, col)
     */
    @Override
    public String toString() 
    {
        return "(" + row + ", " + col + ")";
    }
}
