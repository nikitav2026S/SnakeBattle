

/**
 * Board
 * The origin (0, 0) is at the top left corner.
 *
 * @author itsung30
 * @version October 11, 2026
 */
public class Board
{
    //~ Fields ................................................................
    private int rows;
    private int columns;
    private LinkedChain<Item> itemList;

    //~ Constructors ..........................................................
    public Board(int rows, int columns) {
        if (rows < 3 || columns < 3) {
            throw new IllegalArgumentException("Board minimum dimensions must be 3x3.");
        }
        this.rows = rows;
        this.columns = columns;
    }

    //~Public  Methods ........................................................

    /**
     * @param position
     * @return
     *      Returns true if a point is within the row and column bounds of the board,
     *      and false otherwise.
     */
    public boolean pointWithinBounds(Coordinate position) {
        if (position == null) {
            return false;
        }

        if (position.getX() < 0 || position.getX() > columns - 1) {
            return false;
        }
        if (position.getY() < 0 || position.getY() > rows - 1) {
            return false;
        }

        return true;
    }

    /**
     * Wraps a coordinate onto this board by taking each component modulo
     * the board size. The column is reduced with {@code %} columns and the
     * row with {@code %} rows, then written back onto {@code position}.
     * A null position is returned unchanged.
     * Java's remainder keeps the sign of the dividend, so a negative
     * component stays negative unless it is an exact multiple of the size.
     *
     * @param position the coordinate to wrap, or null
     * @return {@code position} after it has been updated, or null when
     *         {@code position} is null
     */
    public Coordinate wrapCoordinate(Coordinate position) {
        if (position == null) {
            return null;
        }

        int wrappedX = position.getX() % columns;
        int wrappedY = position.getY() % rows;

        position.setX(wrappedX);
        position.setY(wrappedY);
        return position;
    }

    /**
     * Returns a text image of this board, formatted for viewing in the console.
     * Print it with {@code System.out.println(getBoardImage())}.
     *
     * @return a multi-line string representing the board
     */
    public String getBoardImage() {
        throw new UnsupportedOperationException("Not implemented.");
    }

}
