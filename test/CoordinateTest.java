import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test skeleton for {@link Coordinate}.
 *
 * @author itsung30
 * @version October 11, 2026
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
     * column and row. It does not equal null, another type, a different
     * column, or a different row.
     */
    @Test
    public void testEquals() {
        assertTrue(coordinate.equals(coordinate));
        assertTrue(coordinate.equals(new Coordinate(0, 0)));
        assertFalse(coordinate.equals(null));
        assertFalse(coordinate.equals(new Object()));
        assertFalse(coordinate.equals(new Coordinate(1, 0)));
        assertFalse(coordinate.equals(new Coordinate(0, 1)));
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

    /**
     * The text form includes the column and the row.
     */
    @Test
    public void testToString() {
        assertEquals("Coordinate [x=0, y=0]", coordinate.toString());

        coordinate.setX(2);
        coordinate.setY(3);
        assertEquals("Coordinate [x=2, y=3]", coordinate.toString());
    }
}
