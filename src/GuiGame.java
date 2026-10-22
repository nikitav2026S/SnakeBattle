import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GuiGame extends Application {
    private static Snake player1;
    private static Snake player2;
    private static Snake activePlayer;
    private static Board board;
    private static boolean gameOver;
    private Label statusView;
    private GridPane boardView;
    
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

    private void buildGui(Stage primaryStage) {
        boardView = new GridPane();
        boardView.setAlignment(Pos.CENTER);

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
        root.setCenter(boardView);
        root.setBottom(bottom);
        BorderPane.setAlignment(boardView, Pos.CENTER);
        BorderPane.setAlignment(statusView, Pos.CENTER);
        BorderPane.setMargin(statusView, new Insets(0, 0, 12, 0));

        Scene scene = new Scene(root, 360, 480);

        primaryStage.setTitle("Snake Battle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Update the board based on the status of the game.
     */
    private void refreshGui() {
        String status = "Player 1 score: " + player1.getPoints()
            + "\nPlayer 2 score: " + player2.getPoints() + "\n";
        
        String whichPlayerWonMessage = "";
        if (player1.getPoints() > player2.getPoints()) {
            whichPlayerWonMessage = "Player 1 wins.";
        } else if (player1.getPoints() < player2.getPoints()) {
            whichPlayerWonMessage = "Player 2 wins.";
        } else {
            whichPlayerWonMessage = "Tie";
        }

        if (gameOver) {
            status += "You hit something! Game over.\n" + whichPlayerWonMessage;
        }
        else if (gameOver) {
            status += "Player 1's turn.";
        }
        else {
            status += "Player 2's turn.";
        }
        statusView.setText(status);

        BoardRenderer.renderBoardToGridPane(boardView, board);
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
    }

    //#region EVENT HANDLERS
    private void handleUp(ActionEvent event) {
        play(Vector2.UP);
    }

    private void handleDown(ActionEvent event) {
        play(Vector2.DOWN);
    }

    private void handleLeft(ActionEvent event) {
        play(Vector2.LEFT);
    }

    private void handleRight(ActionEvent event) {
        play(Vector2.RIGHT);
    }

    private void handlePlayAgain(ActionEvent event) {
        setupNewGame();
        refreshGui();
    }
    //#endregion
}