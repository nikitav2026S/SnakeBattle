import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

public class CoordinateTest {
    private Coordinate coordinate;

    @Before
    public void setUp() {
        coordinate = new Coordinate(0, 0);
    }

    @Test
    public void testEquals() {
        assertTrue(coordinate.equals(coordinate));
        assertTrue(coordinate.equals(new Coordinate(0, 0)));
        assertFalse(coordinate.equals(null));
        assertFalse(coordinate.equals(new Coordinate(1, 1)));
    }

    @Test
    public void testGetX() {

    }

    @Test
    public void testGetY() {

    }

    @Test
    public void testSetX() {

    }

    @Test
    public void testSetY() {

    }

    @Test
    public void testToString() {

    }
}
