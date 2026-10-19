import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 
public class Viewmodel extends Application {
    private Scene primaryScene;
    
    @Override
    public void start(Stage primaryStage) {
        buildGui(primaryStage);
    }

    public static void main(String[] args) {
        // Calling this creates the window and calls Start().
        // Launch() does not return until the window is closed.
        // It is preferable to run game setup logic in launch() rather than in main().
        launch(args);
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
        primaryScene = scene;

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show(); // Must be called for the window to be rendered.
        System.out.println("DEBUG: ");
        System.out.println("INITIAL SCENE STRUCTURE ===============================================");
        printSceneTree(scene);
        System.out.println("=======================================================================");
    }

    /**
     * Prints a formatted human-readable hierarchy of the scene tree.
     */
    public void printSceneTree(Scene scene) {
        System.out.println("Scene");
        if (scene.getRoot() != null) {
            printNode(scene.getRoot(), 1);
        }
    }

    private void printNode(Node node, int depth) {
        String description = node.getClass().getSimpleName();
        if (node instanceof Labeled labeled) {
            String text = labeled.getText();
            if (text != null && !text.isEmpty()) {
                description += " \"" + text + "\"";
            }
        }
        System.out.println("  ".repeat(depth) + description);
        if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                printNode(child, depth + 1);
            }
        }
    }

    private void handleUp(ActionEvent event) {
        printSceneTree(primaryScene);
    }

    private void handleDown(ActionEvent event) {
        printSceneTree(primaryScene);
    }

    private void handleLeft(ActionEvent event) {
        printSceneTree(primaryScene);
    }

    private void handleRight(ActionEvent event) {
        printSceneTree(primaryScene);
    }
}