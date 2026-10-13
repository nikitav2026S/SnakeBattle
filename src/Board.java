public class Board
{
    //~ Fields ................................................................
    private int rows;
    private int columns;
    private LinkedChain<Item> itemList;

    //~ Constructors ..........................................................
    public Board(int rows, int columns) {
        if (rows < 3 || columns < 3) {
            throw new IllegalArgumentException("Board minimum dimensions must be 3x3.");
        }
        this.rows = rows;
        this.columns = columns;
    }

    //~Public  Methods ........................................................
    public boolean pointWithinBounds(Coordinate position) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    /// System.out.println(getBoardImage()) -- prints board in console, formatted for viewing.
    public String getBoardImage() {
        throw new UnsupportedOperationException("Not implemented.");
    }

}
