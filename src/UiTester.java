import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
/**
 * Test to see if JavaFX works in VSCode.
 * It requires specific setup to run properly in eclipse.
 */
public class UiTester extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        // This is a lambda expression. It is a reflective reference to a block of code.
        // Javafx uses the observer design pattern to recieve UI actions, and the line
        // below is a common example of it in use.
        btn.setOnAction(event -> System.out.println("Hello world"));
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        

        Scene scene = new Scene(root, 300, 250); // This is the area drawn in the window.

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show(); // Must be called for the window to be rendered.
    }

 public static void main(String[] args) {
        launch(args); // Calling this creates the window and calls Start()
    }
}