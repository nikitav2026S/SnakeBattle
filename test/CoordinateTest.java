import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Tests for {@link Coordinate}.
 */
public class CoordinateTest {
    private Coordinate coordinate;

    /**
     * Creates a coordinate at the origin before each test.
     */
    @Before
    public void setUp() {
        coordinate = new Coordinate(0, 0);
    }

    /**
     * A coordinate equals itself and another coordinate with the same
     * column and row, and does not equal null or a different position.
     */
    @Test
    public void testEquals() {
        assertTrue(coordinate.equals(coordinate));
        assertTrue(coordinate.equals(new Coordinate(0, 0)));
        assertFalse(coordinate.equals(null));
        assertFalse(coordinate.equals(new Coordinate(1, 1)));
    }

    /**
     * The column set by the constructor is returned.
     */
    @Test
    public void testGetX() {
        assertTrue(coordinate.getX() == 0);
        assertFalse(coordinate.getX() == 1);
    }

    /**
     * The row set by the constructor is returned.
     */
    @Test
    public void testGetY() {
        assertTrue(coordinate.getY() == 0);
        assertFalse(coordinate.getY() == 1);
    }

    /**
     * Setting the column replaces the previous value.
     */
    @Test
    public void testSetX() {
        assertFalse(coordinate.getX() == 5);
        coordinate.setX(5);
        assertTrue(coordinate.getX() == 5);
    }

    /**
     * Setting the row replaces the previous value.
     */
    @Test
    public void testSetY() {
        assertFalse(coordinate.getY() == 5);
        coordinate.setY(5);
        assertTrue(coordinate.getY() == 5);
    }
}
