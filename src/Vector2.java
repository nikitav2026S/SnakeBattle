/**
 * Represents a 2d integer-based vector. This enables this object to represent both
 * a position on the board as well as a direction of the snake, given that vectors
 * represent both a direction and a magnitude.
 * 
 * Additional note: This class used to be Coordinate. I changed it to be vector-based so it
 * would be capable of representing both position and direction, as well as being 
 * usable in math operations.
 *
 * @author itsung30
 * @version October 11, 2026
 */
public class Vector2
{
    //~ Fields ................................................................
    private int x;
    private int y;

    /**
     * Unit direction one cell toward the top of the board: {@code (0, -1)}.
     * {@code y} increases downward, so moving up decreases the row.
     */
    public static final Vector2 UP = new Vector2(0, -1);

    /**
     * Unit direction one cell toward the bottom of the board: {@code (0, 1)}.
     * {@code y} increases downward, so moving down increases the row.
     */
    public static final Vector2 DOWN = new Vector2(0, 1);

    /**
     * Unit direction one cell toward the left of the board: {@code (-1, 0)}.
     * {@code x} is the column, so moving left decreases it.
     */
    public static final Vector2 LEFT = new Vector2(-1, 0);

    /**
     * Unit direction one cell toward the right of the board: {@code (1, 0)}.
     * {@code x} is the column, so moving right increases it.
     */
    public static final Vector2 RIGHT = new Vector2(1, 0);

    /**
     * The zero vector {@code (0, 0)}. As a position it is the origin (column 0,
     * row 0). As a direction it has no heading and zero length.
     */
    public static final Vector2 ZERO = new Vector2(0, 0);

    //~ Constructors ..........................................................

    /**
     * Creates a vector at the given column and row. {@code y} increases
     * downward, matching the board.
     *
     * @param x the column
     * @param y the row
     */
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates the zero vector.
     */
    public Vector2() {
        this(0, 0);
    }

    //~Public  Methods ........................................................

    /**
     * Returns the column.
     *
     * @return the x value
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the row.
     *
     * @return the y value
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the column.
     *
     * @param x the new x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the row.
     *
     * @param y the new y value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns whether this vector points the same way as {@code direction}.
     * A vector is aligned when it is a positive multiple of that direction,
     * such as {@link #UP}. The zero vector is not aligned with any direction.
     *
     * @param direction the direction to compare against
     * @return true when this vector lies along {@code direction}
     */
    public boolean isFacing(Vector2 direction) {
        if (direction == null || (x == 0 && y == 0)) {
            return false;
        }
        // If the cross product is 0, we know the two vectors are either parallel
        // or pointing in opposite directions.
        int cross = x * direction.getY() - y * direction.getX();
        // If the dot product is greater than 0, we know that the two vectors are pointing
        // more towards the same direction than they are opposite directions.
        int dot = x * direction.getX() + y * direction.getY();
        // By these two conditions, we know if the vector is aligned with another vector.
        return cross == 0 && dot > 0;
    }

    /**
     * Returns the direction to the left of this one. A vector equal to
     * {@link #UP} becomes {@link #LEFT}, and the cycle continues through
     * {@link #DOWN} and {@link #RIGHT}. Any other vector is returned unchanged.
     *
     * @return the left turn
     */
    public Vector2 turnLeft() {
        if (equals(UP)) {
            return LEFT;
        }
        if (equals(LEFT)) {
            return DOWN;
        }
        if (equals(DOWN)) {
            return RIGHT;
        }
        if (equals(RIGHT)) {
            return UP;
        }
        return this;
    }

    /**
     * Returns the direction to the right of this one. A vector equal to
     * {@link #UP} becomes {@link #RIGHT}, and the cycle continues through
     * {@link #DOWN} and {@link #LEFT}. Any other vector is returned unchanged.
     *
     * @return the right turn
     */
    public Vector2 turnRight() {
        if (equals(UP)) {
            return RIGHT;
        }
        if (equals(RIGHT)) {
            return DOWN;
        }
        if (equals(DOWN)) {
            return LEFT;
        }
        if (equals(LEFT)) {
            return UP;
        }
        return this;
    }

    /**
     * Returns the length of the vector with the fractional part discarded.
     * {@code (3, 4)} is {@code 5}, and {@code (1, 1)} is {@code 1}.
     *
     * @return the truncated length
     */
    public int magnitude() {
        return (int) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
    }

    /**
     * Returns a new vector whose components are the sums of this vector and
     * {@code other}. This vector is left unchanged. A null argument returns
     * this vector.
     *
     * @param other the vector to add
     * @return the component-wise sum, or this vector when {@code other} is null
     */
    public Vector2 add(Vector2 other) {
        if (other == null) {
            return this;
        }

        return new Vector2(getX() + other.getX(), getY() + other.getY());
    }

    /**
     * Returns a new vector with {@code amount} added to both components.
     * This vector is left unchanged.
     *
     * @param amount the amount to add to each component
     * @return the shifted vector
     */
    public Vector2 add(int amount) {
        return new Vector2(getX() + amount, getY() + amount);
    }

    /**
     * Returns a new vector whose components are the products of this vector
     * and {@code other}. This vector is left unchanged.
     *
     * @param other the vector to multiply by
     * @return the component-wise product
     * @throws ArithmeticException when {@code other} is null
     */
    public Vector2 multiply(Vector2 other) {
        if (other == null) {
            throw new ArithmeticException();
        }

        return new Vector2(getX() * other.getX(), getY() * other.getY());
    }

    /**
     * Returns a new vector with both components multiplied by {@code factor}.
     * This vector is left unchanged.
     *
     * @param factor the amount to multiply each component by
     * @return the scaled vector
     */
    public Vector2 multiply(int factor) {
        return new Vector2(getX() * factor, getY() * factor);
    }

    /**
     * Returns this vector as {@code Vector2 [x=column, y=row]}.
     *
     * @return a string containing the column and row
     */
    @Override
    public String toString() {
        return "Vector2 [x=" + x + ", y=" + y + "]";
    }

    /**
     * Returns whether this vector has the same column and row as another
     * vector.
     *
     * @param obj the object to compare
     * @return true when {@code obj} is a vector with the same x and y
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2 other = (Vector2) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
}
