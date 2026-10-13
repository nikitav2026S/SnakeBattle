

/**
 * Board - More documentation soon
 * The origin (0, 0) is at the top left corner.
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

    public Coordinate wrapCoordinate(Coordinate position) {
        if (position == null) {
            return new Coordinate(0, 0);
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
