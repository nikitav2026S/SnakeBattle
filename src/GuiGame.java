import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX window for Snake Battle. Two players take turns moving one cell with
 * the arrow buttons. The center of the window shows the board; the top shows
 * scores and whose turn it is. Hitting a wall or the other snake ends the
 * game. Play again starts a new round.
 * <p>
 * Run {@link #main(String[])} or {@code GuiGame.bat}. Game setup belongs in
 * {@link #start(Stage)}, not {@code main}, because {@link #launch(String...)}
 * does not return until the window is closed.
 */
public class GuiGame extends Application {
    private static Snake player1;
    private static Snake player2;
    private static Snake activePlayer;
    private static Board board;
    private static boolean gameOver;
    private Label statusView;
    private GridPane boardViewGridPane;
    
    @Override
    public void start(Stage primaryStage) {
        buildGui(primaryStage);
        setupNewGame();
        refreshGui();
    }

    public static void main(String[] args) {
        // Calling this creates the window and calls Start().
        // Launch() does not return until the window is closed.
        // It is preferable to run game setup logic in start() rather than in main().
        launch(args);
    }

    /**
     * Initial UI setup; includes adding buttons, windows, and creating and
     * showing the window.
     *
     * @param primaryStage
     *            the window to fill and show
     */
    private void buildGui(Stage primaryStage) {
        boardViewGridPane = new GridPane();
        boardViewGridPane.setAlignment(Pos.CENTER);

        statusView = new Label();

        Button upBtn = new Button();
        upBtn.setText("↑");
        upBtn.setOnAction(this::handleUp);

        Button downButton = new Button();
        downButton.setText("↓");
        downButton.setOnAction(this::handleDown);

        Button leftButton = new Button();
        leftButton.setText("←");
        leftButton.setOnAction(this::handleLeft);

        Button rightButton = new Button();
        rightButton.setText("→");
        rightButton.setOnAction(this::handleRight);

        GridPane controls = new GridPane();
        controls.setAlignment(Pos.CENTER);
        controls.setHgap(8);
        controls.setVgap(8);
        controls.add(upBtn, 1, 0);
        controls.add(leftButton, 0, 1);
        controls.add(rightButton, 2, 1);
        controls.add(downButton, 1, 2);

        Button playAgain = new Button("Play again");
        playAgain.setOnAction(this::handlePlayAgain);

        VBox bottom = new VBox(12, controls, playAgain);
        bottom.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(12));
        root.setTop(statusView);
        root.setCenter(boardViewGridPane);
        root.setBottom(bottom);
        BorderPane.setAlignment(boardViewGridPane, Pos.CENTER);
        BorderPane.setAlignment(statusView, Pos.CENTER);
        BorderPane.setMargin(statusView, new Insets(0, 0, 12, 0));

        Scene scene = new Scene(root, 360, 480);

        primaryStage.setTitle("Snake Battle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Update the board based on the status of the game. This must be called
     * after every update to internal game logic. It is best to do so in the button
     * event handlers after all game logic has run.
     */
    private void refreshGui() {
        // 🦃

        // Update the score status label.=========================================
        String status = "Player 1 score: " + player1.getPoints() + "\nPlayer 2 score: " + player2.getPoints() + "\n";
        String whichPlayerWonMessage = "?";

        if (gameOver) {
            if (player1.getPoints() > player2.getPoints()) {
                whichPlayerWonMessage = "Player 1 wins.";
            } else if (player1.getPoints() < player2.getPoints()) {
                whichPlayerWonMessage = "Player 2 wins.";
            } else {
                whichPlayerWonMessage = "Tie";
            }

            status += "You hit something! Game over.\n" + whichPlayerWonMessage;
        }
        else {
            if (activePlayer.equals(player1)) {
                status += "Player 1's turn.";
            } else if (activePlayer.equals(player2)) {
                status += "Player 2's turn.";
            }
        }

        statusView.setText(status);
        // ======================================================================

        // Update the board ui view.
        BoardRenderer.renderBoardToGridPane(boardViewGridPane, board);


    }

    private void setupNewGame() {
        player1 = new Snake(new Vector2(1, 5), Vector2.RIGHT);
        player2 = new Snake(new Vector2(8, 5), Vector2.LEFT);
        activePlayer = player1;
        board = new Board(10, 10);
        board.setSnakes(player1, player2);
        gameOver = false;
    }

    private void play(Vector2 direction) {
        if (gameOver) {
            return;
        }

        if (direction.equals(Vector2.UP)) {
            while (activePlayer.getDirection() != Vector2.UP) {
                activePlayer.turnLeft();
            }
            activePlayer.move(1);
            if (isGameOver()) {
                gameOver = true;
            } else {
                consumePickedUpItems();
            }
        } else if (direction.equals(Vector2.DOWN)) {
            while (activePlayer.getDirection() != Vector2.DOWN) {
                activePlayer.turnLeft();
            }
            activePlayer.move(1);
            if (isGameOver()) {
                gameOver = true;
            } else {
                consumePickedUpItems();
            }
        } else if (direction.equals(Vector2.LEFT)) {
            while (activePlayer.getDirection() != Vector2.LEFT) {
                activePlayer.turnLeft();
            }
            activePlayer.move(1);
            if (isGameOver()) {
                gameOver = true;
            } else {
                consumePickedUpItems();
            }
        } else if (direction.equals(Vector2.RIGHT)) {
            while (activePlayer.getDirection() != Vector2.RIGHT) {
                activePlayer.turnLeft();
            }
            activePlayer.move(1);
            if (isGameOver()) {
                gameOver = true;
            } else {
                consumePickedUpItems();
            }
        }

        swapTurns();
    }

    private boolean isGameOver() {
        boolean passedBoundsCheck = true;
        boolean passedPlayerColliderCheck = true;
        Snake otherSnake = (activePlayer == player1) ? player2 : player1;

        // First check bounds, then player to player collisions.
        if (!board.pointWithinBounds(activePlayer.getHead())) {
            passedBoundsCheck = false;
        }

        // Now check if active player's head is in any of other player's segments.
        if (otherSnake.contains(activePlayer.getHead())) {
            passedPlayerColliderCheck = false;
        }

        return !(passedBoundsCheck && passedPlayerColliderCheck);
    }

    private void swapTurns() {
        activePlayer = (activePlayer == player1) ? player2 : player1;

    }

    /**
     * Remove items overlapping the active snake's head and add them to the
     * score.
     */
    private void consumePickedUpItems() {
        LinkedChain<Item> items = board.getItems();
        for (int i = 0; i < items.size() ; i++) {
            Item current = items.get(i);

            if (activePlayer.getHead().equals(current.getPosition())) {
                items.remove(current);
                activePlayer.addPoints(current.getPoints());
            }
        }
    }

    //#region EVENT HANDLERS
    /**
     * Moves the active snake up. Invoked when the up button is clicked.
     *
     * @param event
     *            the button click
     */
    private void handleUp(ActionEvent event) {
        play(Vector2.UP);
        refreshGui();
    }

    /**
     * Moves the active snake down. Invoked when the down button is clicked.
     *
     * @param event
     *            the button click
     */
    private void handleDown(ActionEvent event) {
        play(Vector2.DOWN);
        refreshGui();
    }

    /**
     * Moves the active snake left. Invoked when the left button is clicked.
     *
     * @param event
     *            the button click
     */
    private void handleLeft(ActionEvent event) {
        play(Vector2.LEFT);
        refreshGui();
    }

    /**
     * Moves the active snake right. Invoked when the right button is clicked.
     *
     * @param event
     *            the button click
     */
    private void handleRight(ActionEvent event) {
        play(Vector2.RIGHT);
        refreshGui();
    }

    /**
     * Starts a new game. Invoked when the play-again button is clicked.
     *
     * @param event
     *            the button click
     */
    private void handlePlayAgain(ActionEvent event) {
        setupNewGame();
        refreshGui();
    }
    //#endregion
}