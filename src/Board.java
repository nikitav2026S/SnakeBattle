
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


    /**
     * Prints this board directly to the console. The origin is the top-left
     * corner. An empty cell is a period, and an item is shown as its point
     * value. Player 1's head is {@code A} and the rest of its path is
     * {@code a}. Player 2's head is {@code B} and the rest of its path is
     * {@code b}. A snake covers an item on the same cell.
     */
    public void printBoard()
    {
        char[][] grid = new char[rows][columns];
        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                grid[y][x] = '.';
            }
        }

        for (int i = 0; i < itemList.size(); i++)
        {
            Item item = itemList.get(i);
            Coordinate position = item.getPosition();
            if (!pointWithinBounds(position))
            {
                continue;
            }
            int x = position.getX();
            int y = position.getY();
            if (grid[y][x] == '.')
            {
                grid[y][x] = (char)('0' + item.getPoints());
            }
        }

        drawSnake(grid, player1, 'A', 'a');
        drawSnake(grid, player2, 'B', 'b');

        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                System.out.print(grid[y][x]);
                if (x < columns - 1)
                {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }


    /**
     * Writes one snake onto the grid. The head is drawn last so it covers
     * the body, an item, or the other snake.
     *
     * @param grid the board being filled
     * @param snake the snake to draw, or null
     * @param head the character for the snake's head
     * @param body the character for the rest of its path
     */
    private void drawSnake(char[][] grid, Snake snake, char head, char body)
    {
        if (snake == null)
        {
            return;
        }

        LinkedChain<Coordinate> path = snake.getPath();
        // Iterate through the snake's chain.
        for (int i = path.size() - 1; i >= 0; i--)
        {
            Coordinate position = path.get(i);
            // Skip points that are beyond bounds.
            if (!pointWithinBounds(position))
            {
                continue;
            }
            
            // If on the first chain of the snake, set the corresponding spot on the grid to
            // be the head.
            if (i == 0) {
                grid[position.getY()][position.getX()] = head;
            } else {
                grid[position.getY()][position.getX()] = body;
            }
        }
    }

}
