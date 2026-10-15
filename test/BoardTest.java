import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test skeleton for {@link Board}.
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
     * A board of at least 3 by 3 can be constructed.
     */
    @Test
    public void testConstructorAcceptsMinimumSize() {
        new Board(3, 3);
        new Board(4, 5);
    }

    /**
     * Fewer than 3 rows is rejected.
     */
    @Test
    public void testConstructorRejectsTooFewRows() {
        try {
            new Board(2, 3);
            fail("Expected IllegalArgumentException for " + 2 + " by " + 3);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Board minimum dimensions must be 3x3.", e.getMessage());
        }

        try {
            new Board(0, 3);
            fail("Expected IllegalArgumentException for " + 0 + " by " + 3);
        }
        catch (IllegalArgumentException e1) {
            assertEquals("Board minimum dimensions must be 3x3.", e1.getMessage());
        }

        int rows = -1;
        try {
            new Board(rows, 3);
            fail("Expected IllegalArgumentException for " + rows + " by " + 3);
        }
        catch (IllegalArgumentException e2) {
            assertEquals("Board minimum dimensions must be 3x3.", e2.getMessage());
        }
    }

    /**
     * Fewer than 3 columns is rejected.
     */
    @Test
    public void testConstructorRejectsTooFewColumns() {
        try {
            new Board(3, 2);
            fail("Expected IllegalArgumentException for " + 3 + " by " + 2);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Board minimum dimensions must be 3x3.", e.getMessage());
        }

        try {
            new Board(3, 0);
            fail("Expected IllegalArgumentException for " + 3 + " by " + 0);
        }
        catch (IllegalArgumentException e1) {
            assertEquals("Board minimum dimensions must be 3x3.", e1.getMessage());
        }
        
        int columns = -1;
        try {
            new Board(3, columns);
            fail("Expected IllegalArgumentException for " + 3 + " by " + columns);
        }
        catch (IllegalArgumentException e2) {
            assertEquals("Board minimum dimensions must be 3x3.", e2.getMessage());
        }
    }

    /**
     * A coordinate inside the board, including the edges, is in bounds.
     */
    @Test
    public void testPointWithinBoundsInside() {
        Coordinate testCoordinate = new Coordinate(0, 0);
        assertTrue(board.pointWithinBounds(testCoordinate));

        testCoordinate.setX(1);
        testCoordinate.setY(1);
        assertTrue(board.pointWithinBounds(testCoordinate));

        testCoordinate.setX(2);
        testCoordinate.setY(2);
        assertTrue(board.pointWithinBounds(testCoordinate));
        
    }

    /**
     * A coordinate past one edge, past both edges, or before the origin is out of bounds.
     */
    @Test
    public void testPointWithinBoundsOutside() {
        Coordinate testCoordinate = new Coordinate(3, 1);
        assertFalse(board.pointWithinBounds(testCoordinate));

        testCoordinate.setX(1);
        testCoordinate.setY(3);
        assertFalse(board.pointWithinBounds(testCoordinate));

        testCoordinate.setX(5);
        testCoordinate.setY(5);
        assertFalse(board.pointWithinBounds(testCoordinate));

        testCoordinate.setX(-1);
        testCoordinate.setY(1);
        assertFalse(board.pointWithinBounds(testCoordinate));

        testCoordinate.setX(1);
        testCoordinate.setY(-1);
        assertFalse(board.pointWithinBounds(testCoordinate));
    }

    /**
     * A null coordinate is out of bounds.
     */
    @Test
    public void testPointWithinBoundsNull() {
        assertFalse(board.pointWithinBounds(null));
    }

    /**
     * The console image is not implemented yet.
     */
    @Test
    public void testGetBoardImageNotImplemented() {
        fail("Not yet implemented");
    }
}
