/**
 * Represents a collectible item on the Snake Battle board.
 * Each item has a position and a point value.
 */
public class Item 
{
    private final Vector2 position;
    private final int points;

    /**
     * Creates an item at the specified position with a point value.
     *
     * @param position the coordinate where the item is located
     * @param points the number of points the item awards
     * @throws IllegalArgumentException if the position is null
     *         or the points are not positive
     */
    public Item(Vector2 position, int points) 
    {
        if (position == null) 
        {
            throw new IllegalArgumentException("Position cannot be null");
        }

        if (points <= 0) 
        {
            throw new IllegalArgumentException("Points must be positive");
        }

        this.position = position;
        this.points = points;
    }

    /**
     * Returns the position of this item.
     *
     * @return the item's coordinate
     */
    public Vector2 getPosition() 
    {
        return position;
    }

    /**
     * Returns the point value of this item.
     *
     * @return the number of points the item awards
     */
    public int getPoints() 
    {
        return points;
    }
}
