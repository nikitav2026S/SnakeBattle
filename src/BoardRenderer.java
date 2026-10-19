/**
 * Provides a unified interface for displaying the board to the user through either console flush or visual GUI.
 */
public class BoardRenderer {

    /**
     * Private constructor to avoid instancing this static class.
     */
    private BoardRenderer() {

    }

    /**
     * Returns a formatted string representation of the board.
     */
    public static String getBoardString(Board board) {
        int boardRows = board.getRows();
        int boardCols = board.getColumns();
        LinkedChain<Item> boardItems = board.getItems();

        char[][] grid = new char[boardRows][boardCols];
        for (int y = 0; y < boardRows; y++)
        {
            for (int x = 0; x < boardCols; x++)
            {
                grid[y][x] = '.';
            }
        }

        for (int i = 0; i < boardItems.size(); i++) {
            Item item = boardItems.get(i);
            Coordinate position = item.getPosition();
            if (!board.pointWithinBounds(position))
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

        drawSnake(board, grid, board.getPlayer1(), 'A', 'a');
        drawSnake(board, grid, board.getPlayer2(), 'B', 'b');

        String ret = "";

        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardCols; x++) {
                ret += grid[y][x];
                if (x < boardCols - 1) {
                    ret += " ";
                }
            }
            ret += "\n";
        }

        return ret;
    }

    /**
     * Flushes the board to the console.
     * @param board
     */
    public static void printBoard(Board board) {
        System.out.println(getBoardString(board));
    }


    private static void drawSnake(Board board, char[][] grid, Snake snake, char head, char body)
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
            if (!board.pointWithinBounds(position))
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
