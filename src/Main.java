import java.util.Scanner;

@SuppressWarnings("javadoc")
public class Main
{
    private static boolean player1Turn = true;
    private static Snake player1;
    private static Snake player2;
    private static Board board;
    public static Scanner input;
    public static boolean gameOver;

    // ----------------------------------------------------------
    /**
     * set up and control loop
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        input = new Scanner(System.in);
        String playAgain = "Y";

        System.out.println("Welcome to Snake Battle!");

        while (playAgain.equalsIgnoreCase("Y"))
        {
            // Initialize a new game
            player1 = new Snake(new Coordinate(1, 5), 1);
            player2 = new Snake(new Coordinate(8, 5), 3);
            board = new Board(10, 10);
            board.setSnakes(player1, player2);

            player1Turn = true;
            gameOver = false;

            System.out.println("Player 1 starts on the left.");
            System.out.println("Player 2 starts on the right.");
            System.out.println("Collect items to earn points.");
            System.out.println("Avoid the walls and both snakes' paths.");

            board.printBoard();

            // Run the current game
            while (!gameOver)
            {
                if (player1Turn)
                {
                    System.out.println("\nPlayer 1's turn.");
                }
                else
                {
                    System.out.println("\nPlayer 2's turn.");
                }

                playerTurn();
            }

            // Display final scores
            System.out.println("Player 1 final score: " + player1.getPoints());
            System.out.println("Player 2 final score: " + player2.getPoints());

            if (player1.getPoints() > player2.getPoints())
            {
                System.out.println("Player 1 wins!");
            }
            else if (player2.getPoints() > player1.getPoints())
            {
                System.out.println("Player 2 wins!");
            }
            else
            {
                System.out.println("It's a tie!");
            }

            // Ask to play again
            System.out.println("Do you want to play again? Y/N");
            playAgain = input.nextLine().trim();
        }

        System.out.println("Thanks for playing!");
        input.close();
    }


    // ----------------------------------------------------------
    /**
     * Check whether an item occupies a point
     * 
     * @param point
     * @return boolean
     */
    public static boolean checkItems(Coordinate point)
    {
        Item[] list = new Item[board.getItems().size()];
        list = board.getItems().toArray(list);
        for (Item item : list)
        {
            if (item.getPosition().equals(point))
            {
                return true;
            }
        }
        return false;
    }


    public static Snake currPlayer()
    {
        if (player1Turn)
        {
            return player1;
        }
        return player2;
    }


    // ----------------------------------------------------------
    /**
     * Read a turn, validate input, turn and move the active snake, then process
     * bounds/collisions/items.
     */
    public static void playerTurn()
    {
        Snake player = currPlayer();

        // get direction
        System.out.println(
            "Enter 'L' to turn left, 'R' to turn right or 'S' to go straight: ");
        String userInput = input.nextLine();

        while (!isValidDirection(userInput))
        {
            System.out.println("Invalid input, try again.");
            System.out.println(
                "Enter 'L' to turn left, 'R' to turn right or 'S' to go straight: ");
            userInput = input.nextLine();
        }

        userInput = userInput.toLowerCase();

        if (userInput.equals("l"))
        {
            player.turnLeft();
        }
        else if (userInput.equals("r"))
        {
            player.turnRight();
        }

        // get distance
        System.out.println("Enter the distance to move: ");
        String distanceInput = input.nextLine();

        while (!isValidDistance(distanceInput))
        {
            System.out.println("Invalid distance, try again.");
            System.out.println("Enter the distance to move: ");
            distanceInput = input.nextLine();
        }

        int distance = Integer.parseInt(distanceInput);
        // move
        player.move(distance);

        // validation
        if (isGameOver(player))
        {
            board.printBoard();
            System.out.println("You hit something! Game over.");
            gameOver = true;
            return;
        }

        // item
        LinkedChain<Item> items = board.getItems();
        LinkedChain<Coordinate> snakePath = player.getPath();

        for (int i = 0; i < snakePath.size(); i++)
        {
            Coordinate point = snakePath.get(i);

            for (int j = 0; j < items.size(); j++)
            {
                Item item = items.get(j);

                if (item.getPosition().equals(point))
                {
                    player.addPoints(item.getPoints());
                    board.removeItem(item);

                    // Stop checking items for this coordinate
                    break;
                }
            }
        }

        board.printBoard();

        System.out.println("Player 1 score: " + player1.getPoints());
        System.out.println("Player 2 score: " + player2.getPoints());

        // Switch players
        player1Turn = !player1Turn;

    }


    // ----------------------------------------------------------
    /**
     * Accept L, R, or S, case-insensitively; reject other input.
     * 
     * @param input
     * @return boolean
     */
    public static boolean isValidDirection(String in)
    {
        if (in == null)
        {
            return false;
        }

        in = in.toLowerCase().trim();
        if (in.equals("l") || in.equals("r") || in.equals("s"))
        {
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Accept a parseable positive integer; reject non-numeric, zero, and
     * negative input.
     * 
     * @param input
     * @return boolean
     */
    public static boolean isValidDistance(String in)
    {
        try
        {
            int distance = Integer.parseInt(in);
            return distance > 0;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }


    // ----------------------------------------------------------
    /**
     * Return true when a loss condition occurs; specify whether this includes
     * wall and snake-path collisions.
     * 
     * @param snake
     * @param otherSnake
     * @return boolean
     */
    public static boolean isGameOver(Snake snake)
    {
        Coordinate head = snake.getHead();

        if (head.getX() < 0 || head.getX() > 9 || head.getY() < 0
            || head.getY() > 9)
        {
            return true;
        }

        Snake otherSnake = (snake == player1) ? player2 : player1;

        if (otherSnake.contains(head))
        {
            return true;
        }

        return false;
    }

}
