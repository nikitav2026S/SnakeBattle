import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Board}.
 *
 * @author itsung30
 * @version October 11, 2026
 */
public class BoardTest {

    private Board board;

    /**
     * Creates a minimum-size board for each test.
     */
    @Before
    public void setUp() {
        board = new Board(3, 3);
    }

    /**
     * A board of at least 3 by 3 can be constructed. Smaller sizes are rejected.
     */
    @Test
    public void testConstructor() {
        new Board(3, 3);
        try {
            new Board(2, 3);
            fail("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Board minimum dimensions must be 3x3.", e.getMessage());
        }
    }

    /**
     * A point inside the board is in bounds. Null is out of bounds.
     */
    @Test
    public void testPointWithinBounds() {
        assertTrue(board.pointWithinBounds(new Vector2(1, 1)));
        assertFalse(board.pointWithinBounds(null));
    }

    /**
     * getRows returns the constructed row count.
     */
    @Test
    public void testGetRows() {
        assertEquals(3, board.getRows());
    }

    /**
     * getColumns returns the constructed column count.
     */
    @Test
    public void testGetColumns() {
        assertEquals(3, board.getColumns());
    }

    /**
     * The constructor fills the board with twelve items.
     */
    @Test
    public void testGetItems() {
        assertEquals(12, board.getItems().size());
    }

    /**
     * Removing a stored item drops it. An item that is not on the board is
     * ignored.
     */
    @Test
    public void testRemoveItem() {
        Item item = board.getItems().get(0);
        board.removeItem(item);
        assertFalse(board.getItems().contains(item));

        int size = board.getItems().size();
        board.removeItem(new Item(new Vector2(0, 0), 1));
        assertEquals(size, board.getItems().size());
    }

    /**
     * setSnakes stores the given snakes. Null clears them.
     */
    @Test
    public void testSetSnakes() {
        Snake player1 = new Snake(new Vector2(0, 0), Vector2.RIGHT);
        Snake player2 = new Snake(new Vector2(2, 2), Vector2.LEFT);
        board.setSnakes(player1, player2);
        assertSame(player1, board.getPlayer1());
        assertSame(player2, board.getPlayer2());

        board.setSnakes(null, null);
        assertNull(board.getPlayer1());
        assertNull(board.getPlayer2());
    }

    /**
     * getPlayer1 returns the first snake after it is stored.
     */
    @Test
    public void testGetPlayer1() {
        Snake player1 = new Snake(new Vector2(0, 0), Vector2.RIGHT);
        board.setSnakes(player1, null);
        assertSame(player1, board.getPlayer1());
        assertNull(new Board(3, 3).getPlayer1());
    }

    /**
     * getPlayer2 returns the second snake after it is stored.
     */
    @Test
    public void testGetPlayer2() {
        Snake player2 = new Snake(new Vector2(2, 2), Vector2.LEFT);
        board.setSnakes(null, player2);
        assertSame(player2, board.getPlayer2());
        assertNull(new Board(3, 3).getPlayer2());
    }
}
