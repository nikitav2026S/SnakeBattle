
/**
 * Board The origin (0, 0) is at the top left corner.
 *
 * @author itsung30
 * @version October 11, 2026
 */
public class Board
{
    // ~ Fields ................................................................
    private int rows;
    private int columns;
    private LinkedChain<Item> itemList;
    private Snake player1;
    private Snake player2;

    // ~ Constructors ..........................................................
    @SuppressWarnings("javadoc")
    public Board(int rows, int columns)
    {
        if (rows < 3 || columns < 3)
        {
            throw new IllegalArgumentException(
                "Board minimum dimensions must be 3x3.");
        }
        this.rows = rows;
        this.columns = columns;

        itemList = new LinkedChain<>();
        for (int i = 0; i < 12; i++)
        {
            Coordinate c = new Coordinate(
                (int)(Math.random() * 10),
                (int)(Math.random() * 10));
            itemList.add(new Item(c, (int)(Math.random() * 3) + 1));
        }
    }

    // ~Public Methods ........................................................


    /**
     * @param position
     * @return Returns true if a point is within the row and column bounds of
     *             the board, and false otherwise.
     */
    public boolean pointWithinBounds(Coordinate position)
    {
        if (position == null)
        {
            return false;
        }

        if (position.getX() < 0 || position.getX() > columns - 1)
        {
            return false;
        }
        if (position.getY() < 0 || position.getY() > rows - 1)
        {
            return false;
        }

        return true;
    }


    /**
     * Wraps a coordinate onto this board by taking each component modulo the
     * board size. The column is reduced with {@code %} columns and the row with
     * {@code %} rows, then written back onto {@code position}. A null position
     * is returned unchanged. Java's remainder keeps the sign of the dividend,
     * so a negative component stays negative unless it is an exact multiple of
     * the size.
     *
     * @param position
     *            the coordinate to wrap, or null
     * @return {@code position} after it has been updated, or null when
     *             {@code position} is null
     */
    public Coordinate wrapCoordinate(Coordinate position)
    {
        if (position == null)
        {
            return null;
        }

        int wrappedX = position.getX() % columns;
        int wrappedY = position.getY() % rows;

        position.setX(wrappedX);
        position.setY(wrappedY);
        return position;
    }


    /**
     * Stores the two snakes so {@link #printBoard()} can draw their paths.
     *
     * @param player1 the first snake, or null
     * @param player2 the second snake, or null
     */
    public void setSnakes(Snake player1, Snake player2)
    {
        this.player1 = player1;
        this.player2 = player2;
    }


    public LinkedChain<Item> getItems()
    {
        return itemList;
    }


    public void removeItem(Item item)
    {
        itemList.remove(item);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Snake getPlayer1() {
        return player1;
    }

    public Snake getPlayer2() {
        return player2;
    }
}
