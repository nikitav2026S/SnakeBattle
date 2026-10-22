// -------------------------------------------------------------------------
/**
 * Represents the player avatar. Stores move history and score of player.
 */
public class Snake
{
    private LinkedChain<Vector2> path;
    private Vector2 direction;
    private int points;

    // ----------------------------------------------------------
    /**
     * Create a new Snake object.
     * 
     * @param point
     * @param startDirection
     */
    public Snake(Vector2 point, Vector2 startDirection)
    {
        path = new LinkedChain<Vector2>();
        path.add(point);
        direction = startDirection;
        points = 0;
    }


    // ----------------------------------------------------------
    /**
     * changes the direction to the left of current direction
     */
    public void turnLeft()
    {
        direction = direction.turnLeft();
    }


    // ----------------------------------------------------------
    /**
     * changes the direction to the right of current direction
     */
    public void turnRight()
    {
        direction = direction.turnRight();
    }


    // ----------------------------------------------------------
    /**
     * adds new Vector2s with input of distance traveled.
     * 
     * @param num
     */
    public void move(int num)
    {
        Vector2 head = getHead();

        for (int i = 0; i < num; i++)
        {
            // Move the snake in the direction it is facing by adding its
            // direction to its position.
            head = head.add(direction);
            path.add(head);
        }

    }


    // ----------------------------------------------------------
    /**
     * returns the head of snake.
     * 
     * @return Vector2
     */
    public Vector2 getHead()
    {
        return path.get(0);
    }


    // ----------------------------------------------------------
    /**
     * return the move history of snake.
     * 
     * @return LinkedChain<Coordinate>
     */
    public LinkedChain<Vector2> getPath()
    {
        return path;
    }


    // ----------------------------------------------------------
    /**
     * returns score of player
     * 
     * @return int
     */
    public int getPoints()
    {
        return points;
    }


    // ----------------------------------------------------------
    /**
     * adds points to the player's score
     * 
     * @param num
     */
    public void addPoints(int num)
    {
        points += num;
    }


    // ----------------------------------------------------------
    /**
     * checks if a point is already with in the snake's path
     * 
     * @param point
     * @return boolean
     */
    public boolean contains(Vector2 point)
    {
        if (point == null)
        {
            return false;
        }
        for (int i = 0; i < path.size(); i++)
        {
            if (path.get(i).equals(point))
                return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * returns current direction
     * 
     * @return Vector2
     */
    public Vector2 getDirection()
    {
        return direction;
    }

}
