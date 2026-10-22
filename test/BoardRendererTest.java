import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import org.junit.Before;
import org.junit.Test;


/**
 * Tests for {@link BoardRenderer}.
 * 
 * @author itsung30
 * @version October 21, 2026
 */
public class BoardRendererTest {

    private Board board;

    /**
     * Builds a 3x3 board with no random items.
     */
    @Before
    public void setUp() {
        board = new Board(3, 3);
    }

    /**
     * Items are placed on the board based on an unseeded random, so the only way
     * to test if the board toString works is to clear the random items.
     */
    @Test
    public void testGetBoardString() {
        Snake player1 = new Snake(new Vector2(0, 0), Vector2.RIGHT);
        board.setSnakes(player1, null);
        LinkedChain<Item> items = board.getItems();

        for (int i = items.size() - 1; i >= 0; i--) {
            board.removeItem(items.get(i));
        }

        assertEquals(
            "A . .\n" +
            ". . .\n" +
            ". . .\n",
            BoardRenderer.getBoardString(board)
        );
   
    }

    /**
     * printBoard writes the board string. A null board is rejected.
     */
    @Test
    public void testPrintBoard() {
        Snake testPlayer = new Snake(new Vector2(0, 0), Vector2.RIGHT);
        board.setSnakes(testPlayer, null);
        BoardRenderer.printBoard(board);
        // How do I even test this? Redirect Stdout? Just look at the picture and press ok?

        try {
            BoardRenderer.printBoard(null);
            fail("Expected NullPointerException.");
        }
        catch (NullPointerException e) {
            assertTrue(e != null);
        }
    }

}
