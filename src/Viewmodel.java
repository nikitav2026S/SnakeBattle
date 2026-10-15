import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 
/**
 * Test to see if JavaFX works in VSCode.
 * It requires specific setup to run properly in eclipse.
 */
public class Viewmodel extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        buildGui(primaryStage);
    }

    public static void main(String[] args) {
        launch(args); // Calling this creates the window and calls Start()
    }

    private void buildGui(Stage primaryStage) {

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
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(8);
        root.setVgap(8);
        root.add(upBtn, 1, 0);
        root.add(leftButton, 0, 1);
        root.add(rightButton, 2, 1);
        root.add(downButton, 1, 2);


        Scene scene = new Scene(root, 300, 250); // This is the area drawn in the window.

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show(); // Must be called for the window to be rendered.
    }

    /**
     * prints a formatted human-readable hierarchy of the scene tree.
     */
    public void printSceneTree() {

    }

    private void handleUp(ActionEvent event) {
        System.out.println(event);
    }

    private void handleDown(ActionEvent event) {
        System.out.println(event);
    }

    private void handleLeft(ActionEvent event) {
        System.out.println(event);
    }

    private void handleRight(ActionEvent event) {
        System.out.println(event);
    }
}