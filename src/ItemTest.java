import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

/**
 * Tests for {@link Item}.
 */
public class ItemTest {

    private Vector2 position;
    private Item item;

    /**
     * Creates an item at (1, 2) worth 3 points.
     */
    @Before
    public void setUp() {
        position = new Vector2(1, 2);
        item = new Item(position, 3);
    }

    /**
     * A valid position and positive points construct an item. Null position
     * and non-positive points are rejected.
     */
    @Test
    public void testConstructor() {
        assertEquals(position, item.getPosition());
        assertEquals(3, item.getPoints());

        try {
            new Item(null, 1);
            fail("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Position cannot be null", e.getMessage());
        }

        try {
            new Item(new Vector2(0, 0), 0);
            fail("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Points must be positive", e.getMessage());
        }
    }

    /**
     * getPoints returns the value given to the constructor.
     */
    @Test
    public void testGetPoints() {
        assertEquals(3, item.getPoints());
    }

    /**
     * getPosition returns the same vector given to the constructor.
     */
    @Test
    public void testGetPosition() {
        assertSame(position, item.getPosition());
    }

    /**
     * getIcon returns an emoji based on the item's point value.
     */
    @Test
    public void testGetIcon() {
        assertEquals("🍇", new Item(position, 1).getIcon());
        assertEquals("🍎", new Item(position, 2).getIcon());
        assertEquals("🐣", item.getIcon());
        assertEquals("🦃", new Item(position, 4).getIcon());
    }
}
