import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test skeleton for {@link Board}.
 */
public class BoardTest {

    private Board board;

    /**
     * Creates a minimum-size board for each test.
     */
    @BeforeEach
    public void setUp() {
        board = new Board(3, 3);
    }

    /**
     * A board of at least 3 by 3 can be constructed.
     */
    @Test
    public void testConstructorAcceptsMinimumSize() {
        fail("Not yet implemented");
    }

    /**
     * Fewer than 3 rows is rejected.
     */
    @Test
    public void testConstructorRejectsTooFewRows() {
        fail("Not yet implemented");
    }

    /**
     * Fewer than 3 columns is rejected.
     */
    @Test
    public void testConstructorRejectsTooFewColumns() {
        fail("Not yet implemented");
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
     * A coordinate past an edge is out of bounds.
     */
    @Test
    public void testPointWithinBoundsOutside() {
        Coordinate testCoordinate = new Coordinate(5, 5);
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
     * A null coordinate wraps to the origin.
     */
    @Test
    public void testWrapCoordinateNull() {
        assertEquals(new Coordinate(0, 0), board.wrapCoordinate(null));
    }

    /**
     * A coordinate already on the board is unchanged, and the same object is returned.
     */
    @Test
    public void testWrapCoordinateAlreadyOnBoard() {
        Coordinate position = new Coordinate(1, 2);
        assertSame(position, board.wrapCoordinate(position));
        assertEquals(new Coordinate(1, 2), position);
    }

    /**
     * A column past the far edge wraps to each remainder, including a second lap.
     */
    @Test
    public void testWrapCoordinatePastFarColumn() {
        assertEquals(new Coordinate(0, 1), board.wrapCoordinate(new Coordinate(3, 1)));
        assertEquals(new Coordinate(1, 1), board.wrapCoordinate(new Coordinate(4, 1)));
        assertEquals(new Coordinate(2, 1), board.wrapCoordinate(new Coordinate(5, 1)));
        assertEquals(new Coordinate(0, 1), board.wrapCoordinate(new Coordinate(6, 1)));
    }

    /**
     * A row past the far edge wraps to each remainder, including a second lap.
     */
    @Test
    public void testWrapCoordinatePastFarRow() {
        assertEquals(new Coordinate(1, 0), board.wrapCoordinate(new Coordinate(1, 3)));
        assertEquals(new Coordinate(1, 1), board.wrapCoordinate(new Coordinate(1, 4)));
        assertEquals(new Coordinate(1, 2), board.wrapCoordinate(new Coordinate(1, 5)));
        assertEquals(new Coordinate(1, 0), board.wrapCoordinate(new Coordinate(1, 6)));
    }

    /**
     * Coordinates past both far edges wrap on each axis independently.
     */
    @Test
    public void testWrapCoordinatePastBothFarEdges() {
        assertEquals(new Coordinate(0, 0), board.wrapCoordinate(new Coordinate(3, 3)));
        assertEquals(new Coordinate(1, 2), board.wrapCoordinate(new Coordinate(4, 5)));
        assertEquals(new Coordinate(2, 1), board.wrapCoordinate(new Coordinate(8, 7)));
    }

    /**
     * Coordinates before the near edge keep Java's negative remainder.
     * A multiple of the board size lands on the origin.
     */
    @Test
    public void testWrapCoordinateBeforeNearEdge() {
        assertEquals(new Coordinate(-1, 1), board.wrapCoordinate(new Coordinate(-1, 1)));
        assertEquals(new Coordinate(1, -2), board.wrapCoordinate(new Coordinate(1, -2)));
        assertEquals(new Coordinate(-1, -2), board.wrapCoordinate(new Coordinate(-4, -5)));
        assertEquals(new Coordinate(0, 0), board.wrapCoordinate(new Coordinate(-3, -6)));
    }

    /**
     * The console image is not implemented yet.
     */
    @Test
    public void testGetBoardImageNotImplemented() {
        fail("Not yet implemented");
    }
}
