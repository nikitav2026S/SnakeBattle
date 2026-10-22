import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for {@link Vector2}.
 *
 * @author itsung30
 * @version October 11, 2026
 */
public class Vector2Test
{
    private Vector2 vector;

    /**
     * Creates a vector at the origin before each test.
     */
    @Before
    public void setUp()
    {
        vector = new Vector2(0, 0);
    }


    /**
     * The two-argument constructor stores the given column and row. The
     * no-argument constructor creates the zero vector.
     */
    @Test
    public void testConstructor()
    {
        assertEquals(0, vector.getX());
        assertEquals(0, vector.getY());
        assertEquals(new Vector2(0, 0), new Vector2());
    }


    /**
     * getX returns the column.
     */
    @Test
    public void testGetX()
    {
        assertEquals(0, vector.getX());
        assertEquals(2, new Vector2(2, 3).getX());
    }


    /**
     * getY returns the row.
     */
    @Test
    public void testGetY()
    {
        assertEquals(0, vector.getY());
        assertEquals(3, new Vector2(2, 3).getY());
    }


    /**
     * setX replaces the column.
     */
    @Test
    public void testSetX()
    {
        vector.setX(5);
        assertEquals(5, vector.getX());
    }


    /**
     * setY replaces the row.
     */
    @Test
    public void testSetY()
    {
        vector.setY(5);
        assertEquals(5, vector.getY());
    }


    /**
     * toString includes the column and row.
     */
    @Test
    public void testToString()
    {
        assertEquals("Vector2 [x=0, y=0]", vector.toString());
    }


    /**
     * A vector equals another with the same column and row. It does not equal
     * null.
     */
    @Test
    public void testEquals()
    {
        assertTrue(vector.equals(new Vector2(0, 0)));
        assertFalse(vector.equals(null));
    }


    /**
     * A vector faces a matching direction. Null is not a facing direction.
     */
    @Test
    public void testIsFacing()
    {
        assertTrue(new Vector2(0, -1).isFacing(Vector2.UP));
        assertFalse(new Vector2(1, 0).isFacing(null));
    }


    /**
     * turnLeft rotates a cardinal direction. A non-cardinal vector is unchanged.
     */
    @Test
    public void testTurnLeft()
    {
        assertEquals(Vector2.LEFT, Vector2.UP.turnLeft());
        Vector2 diagonal = new Vector2(1, 1);
        assertSame(diagonal, diagonal.turnLeft());
    }


    /**
     * turnRight rotates a cardinal direction. A non-cardinal vector is unchanged.
     */
    @Test
    public void testTurnRight()
    {
        assertEquals(Vector2.RIGHT, Vector2.UP.turnRight());
        Vector2 diagonal = new Vector2(1, 1);
        assertSame(diagonal, diagonal.turnRight());
    }


    /**
     * Magnitude is the truncated length. The zero vector has length 0.
     */
    @Test
    public void testMagnitude()
    {
        assertEquals(5, new Vector2(3, 4).magnitude());
        assertEquals(0, vector.magnitude());
    }


    /**
     * add returns the component-wise sum. A null vector argument returns this
     * vector.
     */
    @Test
    public void testAdd()
    {
        assertEquals(new Vector2(3, -1), new Vector2(2, 3).add(new Vector2(1, -4)));
        assertSame(vector, vector.add(null));
        assertEquals(new Vector2(4, 5), new Vector2(2, 3).add(2));
    }


    /**
     * multiply returns the component-wise product. A null vector argument is
     * rejected.
     */
    @Test
    public void testMultiply()
    {
        assertEquals(new Vector2(8, -3),
            new Vector2(2, 3).multiply(new Vector2(4, -1)));
        assertEquals(new Vector2(6, 9), new Vector2(2, 3).multiply(3));
        try {
            vector.multiply(null);
            fail("Expected ArithmeticException.");
        }
        catch (ArithmeticException e) {
            assertNotNull(e);
        }
    }
}
