import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test skeleton for {@link Vector2}.
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
     * A vector equals itself and another vector with the same column
     * and row. It does not equal null, another type, a different column, or a
     * different row.
     */
    @Test
    public void testEquals()
    {
        assertTrue(vector.equals(vector));
        assertTrue(vector.equals(new Vector2(0, 0)));
        assertFalse(vector.equals(null));
        assertFalse(vector.equals(new Object()));
        assertFalse(vector.equals(new Vector2(1, 0)));
        assertFalse(vector.equals(new Vector2(0, 1)));
    }


    /**
     * The column set by the constructor is returned.
     */
    @Test
    public void testGetX()
    {
        assertTrue(vector.getX() == 0);
        assertFalse(vector.getX() == 1);
    }


    /**
     * The row set by the constructor is returned.
     */
    @Test
    public void testGetY()
    {
        assertTrue(vector.getY() == 0);
        assertFalse(vector.getY() == 1);
    }


    /**
     * Setting the column replaces the previous value.
     */
    @Test
    public void testSetX()
    {
        assertFalse(vector.getX() == 5);
        vector.setX(5);
        assertTrue(vector.getX() == 5);
    }


    /**
     * Setting the row replaces the previous value.
     */
    @Test
    public void testSetY()
    {
        assertFalse(vector.getY() == 5);
        vector.setY(5);
        assertTrue(vector.getY() == 5);
    }


    /**
     * The text form includes the column and the row.
     */
    @Test
    public void testToString()
    {
        assertEquals("Vector2 [x=0, y=0]", vector.toString());

        vector.setX(2);
        vector.setY(3);
        assertEquals("Vector2 [x=2, y=3]", vector.toString());
    }


    /**
     * The no-argument constructor creates the zero vector, and the direction
     * constants are the four unit steps on the board.
     */
    @Test
    public void testDirections()
    {
        assertEquals(new Vector2(0, 0), new Vector2());
        assertEquals(new Vector2(0, 0), Vector2.ZERO);
        assertEquals(new Vector2(0, -1), Vector2.UP);
        assertEquals(new Vector2(0, 1), Vector2.DOWN);
        assertEquals(new Vector2(-1, 0), Vector2.LEFT);
        assertEquals(new Vector2(1, 0), Vector2.RIGHT);
        assertEquals(Vector2.LEFT, Vector2.UP.turnLeft());
        assertEquals(Vector2.RIGHT, Vector2.UP.turnRight());
        assertEquals(Vector2.UP, Vector2.LEFT.turnRight());
    }


    /**
     * Magnitude is the truncated length of the vector.
     */
    @Test
    public void testMagnitude()
    {
        assertEquals(0, vector.magnitude());
        assertEquals(5, new Vector2(3, 4).magnitude());
        assertEquals(5, new Vector2(-3, -4).magnitude());
        assertEquals(1, new Vector2(1, 0).magnitude());
        assertEquals(1, new Vector2(1, 1).magnitude());
    }


    /**
     * A vector faces a direction constant when it is a positive multiple of
     * that direction. The opposite direction, a diagonal, and the zero vector
     * do not.
     */
    @Test
    public void testIsFacing()
    {
        assertTrue(new Vector2(0, -1).isFacing(Vector2.UP));
        assertTrue(new Vector2(0, -4).isFacing(Vector2.UP));
        assertTrue(new Vector2(0, 3).isFacing(Vector2.DOWN));
        assertTrue(new Vector2(-2, 0).isFacing(Vector2.LEFT));
        assertTrue(new Vector2(5, 0).isFacing(Vector2.RIGHT));

        assertFalse(new Vector2(0, 4).isFacing(Vector2.UP));
        assertFalse(new Vector2(1, 1).isFacing(Vector2.RIGHT));
        assertFalse(new Vector2(-4, 2).isFacing(Vector2.LEFT));
        assertFalse(vector.isFacing(Vector2.UP));
        assertFalse(new Vector2(1, 0).isFacing(null));
    }


    /**
     * Addition returns the component-wise sum without changing either vector.
     * A null argument returns the same vector.
     */
    @Test
    public void testAdd()
    {
        Vector2 sum = new Vector2(2, 3).add(new Vector2(1, -4));
        assertEquals(new Vector2(3, -1), sum);
        assertEquals(new Vector2(4, 5), new Vector2(2, 3).add(2));
        assertEquals(new Vector2(0, 0), vector.add(Vector2.ZERO));
        assertSame(vector, vector.add(null));
    }


    /**
     * Multiplication returns the component-wise product. A null argument
     * throws {@link ArithmeticException}.
     */
    @Test
    public void testMultiply()
    {
        Vector2 product = new Vector2(2, 3).multiply(new Vector2(4, -1));
        assertEquals(new Vector2(8, -3), product);
        assertEquals(new Vector2(6, 9), new Vector2(2, 3).multiply(3));
        assertEquals(Vector2.ZERO, new Vector2(5, 6).multiply(Vector2.ZERO));
    }


    /**
     * Multiplying by null is rejected.
     */
    @Test
    public void testMultiplyNull()
    {
        try {
            vector.multiply(null);
            fail("Expected ArithmeticException.");
        } catch (ArithmeticException e) {
            assertNotNull(e);
        }
        
    }
}
