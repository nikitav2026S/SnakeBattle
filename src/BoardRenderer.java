import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * Provides a unified interface for displaying the board to the user through
 * either console flush or visual GUI.
 */
public class BoardRenderer {

    private static final Font EMOJI_FONT = Font.font("Segoe UI Emoji", 20);

    /**
     * Private constructor to avoid instancing this static class.
     */
    private BoardRenderer() {
    }

    /**
     * Returns a matrix of Labels corresponding to the state of the board. These
     * are meant to be held by the UI gridpane.
     */
    private static Label[][] getBoardLabelMatrix(Board board) {
        int boardRows = board.getRows();
        int boardCols = board.getColumns();
        Label[][] cells = new Label[boardRows][boardCols];
        LinkedChain<Item> boardItems = board.getItems();

        // Fill the label matrix with new label objects.
        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardCols; x++) {
                Label label = new Label();
                label.setFont(EMOJI_FONT);
                label.setAlignment(Pos.CENTER);
                cells[y][x] = label;
            }
        }

        // First fill the label matrix with empty spaces.
        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardCols; x++) {
                Label label = cells[y][x];

                label.setText("⬛");
            }
        }

        // Now iterate through the items and place them.
        for (int i = 0; i < boardItems.size(); i++) {
            Item item = boardItems.get(i);
            Vector2 position = item.getPosition();
            if (!board.pointWithinBounds(position)) {
                continue;
            }
            int x = position.getX();
            int y = position.getY();

            Label label = cells[y][x];
            
            label.setText(item.getIcon());
        }

        // Finally we place the snakes.
        // This requires iterating through a matrix of placed snakes, and then
        // casting them to strings and placing them in the labels one by one.
        String[][] dummyGrid = new String[boardRows][boardCols];
        drawSnake(board, dummyGrid, board.getPlayer1(), "🐍", "🟦");
        drawSnake(board, dummyGrid, board.getPlayer2(), "🐍", "🟥");

        // At this point dummyGrid contains a matrix full of nulls, but with snake strings
        // placed at the right positions.
        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardCols; x++) {
                Label label = cells[y][x];
                String possibleSnake = dummyGrid[y][x];

                if (possibleSnake != null) {
                    label.setText(possibleSnake);
                }
            }
        }

        return cells;
    }

    /**
     * Replaces the children of {@code uiGrid} with one label per board cell.
     * Empty cells are {@code ⬛}, items are {@code 🍎}, player 1 is
     * {@code 🐍}/{@code 🟦}, and player 2 is {@code 🐍}/{@code 🟥}. Out-of-bounds
     * items and snake segments are skipped. Existing children are removed first.
     *
     * @param uiGrid
     *            the grid to fill; must not be null
     * @param board
     *            the board to draw; must not be null
     * @throws NullPointerException
     *             if {@code uiGrid} or {@code board} is null
     */
    public static void renderBoardToGridPane(GridPane uiGrid, Board board) {
        // Wipe all old children.
        uiGrid.getChildren().clear();

        // Get a matrix of labels.
        Label[][] cells = getBoardLabelMatrix(board);
        // Fill the UiGrid with label children from the matrix.
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                uiGrid.add(cells[y][x], x, y);
            }
        }
    }

    /**
     * Returns a formatted string representation of the board.
     * <pre>
     * a a A . .
     * . 02 . . .
     * . . . b B
     * . . . . .
     * </pre>
     *
     * @param board
     *            the board to format; must not be null
     * @return the board as a multi-line string
     * @throws NullPointerException
     *             if {@code board} is null
     */
    public static String getBoardString(Board board) {
        int boardRows = board.getRows();
        int boardCols = board.getColumns();
        LinkedChain<Item> boardItems = board.getItems();

        String[][] grid = new String[boardRows][boardCols];
        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardCols; x++) {
                grid[y][x] = ".";
            }
        }

        for (int i = 0; i < boardItems.size(); i++) {
            Item item = boardItems.get(i);
            Vector2 position = item.getPosition();
            if (!board.pointWithinBounds(position)) {
                continue;
            }
            int x = position.getX();
            int y = position.getY();
            if (grid[y][x] == ".") {
                grid[y][x] = ("0" + item.getPoints());
            }
        }

        drawSnake(board, grid, board.getPlayer1(), "A", "a");
        drawSnake(board, grid, board.getPlayer2(), "B", "b");

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
     * Writes {@link #getBoardString(Board)} to standard output, followed by a
     * newline.
     *
     * @param board
     *            the board to print, cannot must not be null
     * @throws NullPointerException
     *             if {@code board} is null
     */
    public static void printBoard(Board board) {
        System.out.println(getBoardString(board));
    }

    private static void drawSnake(Board board, String[][] grid, Snake snake, String head, String body) {
        if (snake == null) {
            return;
        }

        LinkedChain<Vector2> path = snake.getPath();
        // Iterate through the snake's chain.
        for (int i = path.size() - 1; i >= 0; i--) {
            Vector2 position = path.get(i);
            // Skip points that are beyond bounds.
            if (!board.pointWithinBounds(position)) {
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
