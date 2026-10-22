
import student.TestCase;

public class SnakeTest
    extends TestCase
{
    // ~ Fields ........................................................
    private Snake snake;
    private Vector2 start;

    // ~ Set Up ........................................................
    public void setUp()
    {
        start = new Vector2(5, 5);
        snake = new Snake(start, Vector2.UP);
    }

    // ~ Test Methods ..................................................


    // ----------------------------------------------------------
    /**
     * Test the constructor.
     */
    public void testConstructor()
    {
        assertEquals(1, snake.getPath().size());
        assertEquals(start, snake.getHead());
        assertEquals(0, snake.getPoints());
        assertEquals(Vector2.UP, snake.getDirection());
    }


    // ----------------------------------------------------------
    /**
     * Test turnLeft().
     */
    public void testTurnLeft()
    {
        snake.turnLeft();
        assertEquals(Vector2.LEFT, snake.getDirection());

        snake = new Snake(new Vector2(5, 5), Vector2.DOWN);
        snake.turnLeft();
        assertEquals(Vector2.RIGHT, snake.getDirection());
    }


    // ----------------------------------------------------------
    /**
     * Test turnRight().
     */
    public void testTurnRight()
    {
        snake.turnRight();
        assertEquals(Vector2.RIGHT, snake.getDirection());

        snake = new Snake(new Vector2(5, 5), Vector2.LEFT);
        snake.turnRight();
        assertEquals(Vector2.UP, snake.getDirection());
    }


    // ----------------------------------------------------------
    /**
     * Test move() in all four directions.
     */
    public void testMove()
    {
        // Up
        snake.move(3);
        assertEquals(new Vector2(5, 2), snake.getHead());

        // Right
        snake = new Snake(new Vector2(5, 5), Vector2.RIGHT);
        snake.move(3);
        assertEquals(new Vector2(8, 5), snake.getHead());

        // Down
        snake = new Snake(new Vector2(5, 5), Vector2.DOWN);
        snake.move(3);
        assertEquals(new Vector2(5, 8), snake.getHead());

        // Left
        snake = new Snake(new Vector2(5, 5), Vector2.LEFT);
        snake.move(3);
        assertEquals(new Vector2(2, 5), snake.getHead());
    }


    // ----------------------------------------------------------
    /**
     * Test that move() adds a coordinate to the path.
     */
    public void testMoveAddsToPath()
    {
        assertEquals(1, snake.getPath().size());

        snake.move(2);

        assertEquals(2, snake.getPath().size());
    }


    // ----------------------------------------------------------
    /**
     * Test getHead().
     */
    public void testGetHead()
    {
        assertSame(start, snake.getHead());
    }


    // ----------------------------------------------------------
    /**
     * Test getPath().
     */
    public void testGetPath()
    {
        assertNotNull(snake.getPath());
        assertEquals(start, snake.getPath().get(0));
    }


    // ----------------------------------------------------------
    /**
     * Test getPoints() and addPoints().
     */
    public void testPoints()
    {
        assertEquals(0, snake.getPoints());

        snake.addPoints(10);
        assertEquals(10, snake.getPoints());

        snake.addPoints(5);
        assertEquals(15, snake.getPoints());
    }


    // ----------------------------------------------------------
    /**
     * Test contains() with coordinates on and off the path.
     */
    public void testContains()
    {
        assertTrue(snake.contains(new Vector2(5, 5)));
        assertFalse(snake.contains(new Vector2(9, 9)));
        assertFalse(snake.contains(null));

        snake.move(3);

        assertTrue(snake.contains(new Vector2(5, 2)));
    }
}
