
import student.TestCase;

public class SnakeTest
    extends TestCase
{
    // ~ Fields ........................................................
    private Snake snake;
    private Coordinate start;

    // ~ Set Up ........................................................
    public void setUp()
    {
        start = new Coordinate(5, 5);
        snake = new Snake(start, 0);
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
        assertEquals(0, snake.getDirection());
    }


    // ----------------------------------------------------------
    /**
     * Test turnLeft().
     */
    public void testTurnLeft()
    {
        snake.turnLeft();
        assertEquals(3, snake.getDirection());

        snake = new Snake(new Coordinate(5, 5), 2);
        snake.turnLeft();
        assertEquals(1, snake.getDirection());
    }


    // ----------------------------------------------------------
    /**
     * Test turnRight().
     */
    public void testTurnRight()
    {
        snake.turnRight();
        assertEquals(1, snake.getDirection());

        snake = new Snake(new Coordinate(5, 5), 3);
        snake.turnRight();
        assertEquals(0, snake.getDirection());
    }


    // ----------------------------------------------------------
    /**
     * Test move() in all four directions.
     */
    public void testMove()
    {
        // Up
        snake.move(3);
        assertEquals(new Coordinate(5, 2), snake.getHead());

        // Right
        snake = new Snake(new Coordinate(5, 5), 1);
        snake.move(3);
        assertEquals(new Coordinate(8, 5), snake.getHead());

        // Down
        snake = new Snake(new Coordinate(5, 5), 2);
        snake.move(3);
        assertEquals(new Coordinate(5, 8), snake.getHead());

        // Left
        snake = new Snake(new Coordinate(5, 5), 3);
        snake.move(3);
        assertEquals(new Coordinate(2, 5), snake.getHead());
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
        assertTrue(snake.contains(new Coordinate(5, 5)));
        assertFalse(snake.contains(new Coordinate(9, 9)));
        assertFalse(snake.contains(null));

        snake.move(3);

        assertTrue(snake.contains(new Coordinate(5, 2)));
    }
}
