// -------------------------------------------------------------------------
/**
 * Represents the player avatar. Stores move history and score of player.
 */
public class Snake
{
    private LinkedChain<Coordinate> path;
    private int direction;
    private int points;

    // ----------------------------------------------------------
    /**
     * Create a new Snake object.
     * 
     * @param point
     * @param startDirection
     */
    public Snake(Coordinate point, int startDirection)
    {
        path = new LinkedChain<Coordinate>();
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
        if (direction == 0)
        {
            direction = 3;
        }
        else
        {
            direction = (direction - 1) % 4;
        }
    }


    // ----------------------------------------------------------
    /**
     * changes the direction to the right of current direction
     */
    public void turnRight()
    {
        direction = (direction + 1) % 4;
    }


    // ----------------------------------------------------------
    /**
     * adds a new coordinate with input of distance traveled.
     * 
     * @param num
     */
    @SuppressWarnings("incomplete-switch")
    public void move(int num)
    {
        int x = getHead().getX();
        int y = getHead().getY();

        switch (direction)
        {
            case (0):
                y -= num;
                break;
            case (1):
                x += num;
                break;
            case (2):
                y += num;
                break;
            case (3):
                x -= num;
                break;
        }
        Coordinate newHead = new Coordinate(x, y);
        path.add(newHead);
    }


    // ----------------------------------------------------------
    /**
     * returns the head of snake.
     * 
     * @return Coordinate
     */
    public Coordinate getHead()
    {
        return path.get(0);
    }


    // ----------------------------------------------------------
    /**
     * return the move history of snake.
     * 
     * @return LinkedChain<Coordinate>
     */
    public LinkedChain<Coordinate> getPath()
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
    public boolean contains(Coordinate point)
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
     * @return int
     */
    public int getDirection()
    {
        return direction;
    }

}
